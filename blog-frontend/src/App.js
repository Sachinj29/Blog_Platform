import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/common/Navbar';
import HomePage from './pages/HomePage';
import LoginPage from './pages/LoginPage';
import RegisterPage from './pages/RegisterPage';
import PostDetailsPage from './pages/PostDetailsPage';
import CreatePostPage from './pages/CreatePostPage';
import { AuthProvider } from './context/AuthContext';
import './App.css';

function App() {
  return (
    <AuthProvider>
      <div className="App">
        <Navbar />
        <main style={{ padding: '1rem' }}>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/posts/:id" element={<PostDetailsPage />} />
            <Route path="/create-post" element={<CreatePostPage />} />
          </Routes>
        </main>
      </div>
    </AuthProvider>
  );
}

export default App;
