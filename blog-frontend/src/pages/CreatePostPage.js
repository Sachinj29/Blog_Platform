import React from 'react';
import { useNavigate } from 'react-router-dom';
import { createPost } from '../api/postApi';
import PostForm from '../components/posts/PostForm';

const CreatePostPage = () => {
  const navigate = useNavigate();

  const handleCreatePost = async (postData) => {
    try {
      const response = await createPost(postData);
      navigate(`/posts/${response.data.id}`); // Navigate to the new post's detail page
    } catch (error) {
      console.error("Failed to create post:", error);
      alert("Error creating post. Please try again.");
    }
  };

  return (
    <div>
      <h1>Create a New Post</h1>
      <PostForm onSubmit={handleCreatePost} />
    </div>
  );
};

export default CreatePostPage;
