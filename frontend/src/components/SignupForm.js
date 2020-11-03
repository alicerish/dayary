import React, { useState } from 'react';
import { signUp } from '../apis';

import './index.scss';

const SignupForm = () => {
    const [id, setId] = useState('');
    const [password, setPassword] = useState('');
    const [form, setForm] = useState(true);

    const handleFormChange = () => {
        setForm(!form);
    };

    const handleChange = (e) => {
        if (e.currentTarget.name === 'id') {
            setId(e.currentTarget.value);
        } else {
            setPassword(e.currentTarget.value);
        }
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        signUp({ id: id, password: password });
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
                <button className="submit-btn" onClick={handleSubmit}>
                    Sign up
                </button>
            </div>

            <div className={`login ${form ? 'slide-up' : ''}`}>
                <div className="center">
                    <h2
                        className="form-title"
                        id="login"
                        onClick={handleFormChange}
                    >
                        <span>or</span>Log in
                    </h2>
                    <div className="form-holder">
                        <input
                            type="email"
                            className="input"
                            placeholder="Id"
                        />
                        <input
                            type="password"
                            className="input"
                            placeholder="Password"
                        />
                    </div>
                    <button className="submit-btn">Log In</button>
                </div>
            </div>
        </div>
    );
};

export default SignupForm;
