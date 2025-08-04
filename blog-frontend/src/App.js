import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Navbar from './components/common/Navbar.js';
import HomePage from './pages/HomePage.js';
import LoginPage from './pages/LoginPage.js';
import RegisterPage from './pages/RegisterPage.js';
import PostDetailsPage from './pages/PostDetailsPage.js';
import CreatePostPage from './pages/CreatePostPage.js';
import UpdatePostPage from './pages/UpdatePostPage.js'; // <-- Import new page
import { AuthProvider } from './context/AuthContext.js';
import './App.css';

function App() {
  return (
    <AuthProvider>
      <div className="App">
        <Navbar />
        <main>
          <Routes>
            <Route path="/" element={<HomePage />} />
            <Route path="/login" element={<LoginPage />} />
            <Route path="/register" element={<RegisterPage />} />
            <Route path="/posts/:id" element={<PostDetailsPage />} />
            <Route path="/create-post" element={<CreatePostPage />} />
            <Route path="/posts/:id/edit" element={<UpdatePostPage />} /> {/* <-- Add new route */}
          </Routes>
        </main>
      </div>
    </AuthProvider>
  );
}

export default App;
