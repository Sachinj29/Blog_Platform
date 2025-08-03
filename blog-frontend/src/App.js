import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/common/Navbar.js'; // Added .js
import HomePage from './pages/HomePage.js'; // Added .js
import LoginPage from './pages/LoginPage.js'; // Added .js
import RegisterPage from './pages/RegisterPage.js'; // Added .js
import PostDetailsPage from './pages/PostDetailsPage.js'; // Added .js
import CreatePostPage from './pages/CreatePostPage.js'; // Added .js
import { AuthProvider } from './context/AuthContext.js'; // Added .js
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
