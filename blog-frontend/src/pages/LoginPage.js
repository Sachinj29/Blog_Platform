import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { loginUser } from '../api/authApi.js';
import { useAuth } from '../hooks/useAuth.js';
import './FormStyles.css'; // Import shared form styles

const LoginPage = () => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        try {
          const response = await loginUser({ username, password });
          login(response.data.accessToken);
          navigate('/');
        } catch (err) {
          setError('Login failed. Please check your credentials.');
          console.error(err);
        }
    };

    return (
        <div className="form-container">
            <h2>Login</h2>
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username</label>
                    <input type="text" value={username} onChange={e => setUsername(e.target.value)} required />
                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" value={password} onChange={e => setPassword(e.target.value)} required />
                </div>
                <button type="submit" className="form-button">Login</button>
            </form>
            {error && <p className="form-error">{error}</p>}
        </div>
    );
};

export default LoginPage;
