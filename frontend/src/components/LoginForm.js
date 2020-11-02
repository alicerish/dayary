import React, { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';

const LoginForm = () => {
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
        console.log('로그인 요청');
    };

    return (
        <div>
            <form noValidate autoComplete="off">
                <input name="id" onChange={handleChange}></input>
                <input name="password" onChange={handleChange}></input>
                <button onClick={handleSubmit}>로그인</button>
            </form>
            <Link to="/signup">회원가입</Link>
        </div>
    );
};

export { LoginForm };
