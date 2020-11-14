import React, { useState } from 'react';
import { useHistory } from 'react-router-dom';
import { logIn, signUp } from '../apis';

import './index.scss';

const EntryForm = () => {
    let history = useHistory();
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [form, setForm] = useState(true);

    const [token, setToken] = useState('');

    React.useEffect(() => {
        if (sessionStorage.getItem('_jwt')) {
            sessionStorage.removeItem('_jwt');

            handleLogout();
        }

        if (token) {
            sessionStorage.setItem('_jwt', token);

            history.push('/main');
        }
    }, [token]);

    const handleLogout = async () => {
        document.location.href = '/';
    };

    // form 변경 시 입력 정보 reset
    const handleFormChange = () => {
        setForm(!form);
        setId('');
        setPassword('');
    };

    // input값 변경
    const handleChange = (e) => {
        if (e.currentTarget.name === 'id') {
            setId(e.currentTarget.value);
        } else {
            setPassword(e.currentTarget.value);
        }
    };

    const handleSignUp = (e) => {
        e.preventDefault();
        signUp({ id: id, password: password })
            .then(() => {
                console.log('signUp!');
            })
            .catch(() => {
                console.log('error');
            });
    };

    const handleLogIn = (e) => {
        e.preventDefault();
        logIn({ id: id, password: password })
            .then((res) => {
                setToken(res._jwt);
                localStorage.setItem('_jwt', res._jwt);
            })
            .catch(() => {
                console.log('로그인 실패');
            });
    };

    return (
        <div>
            <div className={`signup ${!form ? 'slide-up' : ''}`}>
                <h2
                    className="form-title"
                    id="signup"
                    onClick={handleFormChange}
                >
                    <span>or</span>Sign up
                </h2>
                <div className="form-holder">
                    <input
                        className="input"
                        name="id"
                        autoComplete="off"
                        placeholder="Id"
                        onChange={handleChange}
                    ></input>
                    <input
                        className="input"
                        name="password"
                        placeholder="password"
                        onChange={handleChange}
                    ></input>
                    <input
                        className="input"
                        name="password_chk"
                        placeholder="password check"
                        onChange={handleChange}
                    ></input>
                </div>
                <button className="submit-btn" onClick={handleSignUp}>
                    Sign up
                </button>
            </div>

            <div className={`login ${form ? 'slide-up' : ''}`}>
                <div className="center">
                    <h2
                        className="form-title"
                        id="logIn"
                        onClick={handleFormChange}
                    >
                        <span>or</span>Log in
                    </h2>
                    <div className="form-holder">
                        <input
                            name="id"
                            className="input"
                            placeholder="Id"
                            onChange={handleChange}
                        />
                        <input
                            type="password"
                            name="password"
                            className="input"
                            placeholder="Password"
                            onChange={handleChange}
                        />
                    </div>
                    <button className="submit-btn" onClick={handleLogIn}>
                        Log In
                    </button>
                </div>
            </div>
        </div>
    );
};

export default EntryForm;
