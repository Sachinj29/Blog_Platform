import { useContext } from 'react';
import { AuthContext } from '../context/AuthContext.js'; // Added .js

export const useAuth = () => {
  return useContext(AuthContext);
};
