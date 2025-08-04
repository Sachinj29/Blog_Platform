import React, { createContext, useState, useEffect } from 'react';
import { jwtDecode } from 'jwt-decode'; // <-- Import the new library

export const AuthContext = createContext(null);

export const AuthProvider = ({ children }) => {
  const [token, setToken] = useState(null);
  const [currentUser, setCurrentUser] = useState(null); // <-- New state for user info

  useEffect(() => {
    const storedToken = localStorage.getItem('jwtToken');
    if (storedToken) {
      setToken(storedToken);
      const decodedUser = jwtDecode(storedToken);
      // The username is in the 'sub' (subject) claim of the JWT
      setCurrentUser({ username: decodedUser.sub });
    }
  }, []);

  const login = (newToken) => {
    localStorage.setItem('jwtToken', newToken);
    setToken(newToken);
    const decodedUser = jwtDecode(newToken);
    setCurrentUser({ username: decodedUser.sub });
  };

  const logout = () => {
    localStorage.removeItem('jwtToken');
    setToken(null);
    setCurrentUser(null);
  };

  const authContextValue = {
    token,
    currentUser, // <-- Expose the user info
    login,
    logout,
  };

  return (
    <AuthContext.Provider value={authContextValue}>
      {children}
    </AuthContext.Provider>
  );
};
