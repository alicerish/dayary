import React from 'react';
import { signUp } from '../apis';

const SignupForm = () => {
    const [id, setId] = React.useState('');
    const [password, setPassword] = React.useState('');

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
        console.log('회원가입 요청');
    };

    return (
        <div>
            <form noValidate autoComplete="off">
                <input name="id" onChange={handleChange}></input>
                <input name="password" onChange={handleChange}></input>
                <button onClick={handleSubmit}>회원가입</button>
            </form>
        </div>
    );
};

export default SignupForm;
