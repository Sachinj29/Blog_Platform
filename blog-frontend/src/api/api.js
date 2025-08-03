import axios from 'axios';

// Create a new axios instance
const api = axios.create({
  baseURL: 'http://localhost:8081/api', // Your Spring Boot API base URL
});

// Add a request interceptor to include the token in headers
api.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem('jwtToken');
    if (token) {
      config.headers['Authorization'] = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default api;
