import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useAuth } from '../../hooks/useAuth';

const Navbar = () => {
  const { token, logout } = useAuth();
  const navigate = useNavigate();

  const handleLogout = () => {
    logout();
    navigate('/login');
  };

  return (
    <nav style={{ display: 'flex', justifyContent: 'space-between', padding: '1rem', background: '#eee' }}>
      <div>
        <Link to="/" style={{ marginRight: '1rem' }}>Home</Link>
        {token && <Link to="/create-post">Create Post</Link>}
      </div>
      <div>
        {token ? (
          <button onClick={handleLogout}>Logout</button>
        ) : (
          <>
            <Link to="/login" style={{ marginRight: '1rem' }}>Login</Link>
            <Link to="/register">Register</Link>
          </>
        )}
      </div>
    </nav>
  );
};

export default Navbar;
