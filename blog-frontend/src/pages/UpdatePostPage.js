import React, { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { getPostById, updatePost } from '../api/postApi.js';
import PostForm from '../components/posts/PostForm.js';

const UpdatePostPage = () => {
  const [initialData, setInitialData] = useState(null);
  const { id } = useParams();
  const navigate = useNavigate();

  useEffect(() => {
    const fetchPost = async () => {
      try {
        const response = await getPostById(id);
        setInitialData(response.data);
      } catch (error) {
        console.error("Failed to fetch post for editing:", error);
        navigate('/'); // Redirect if post not found or error
      }
    };
    fetchPost();
  }, [id, navigate]);

  const handleUpdatePost = async (postData) => {
    try {
      await updatePost(id, postData);
      navigate(`/posts/${id}`); // Redirect to the post detail page after update
    } catch (error) {
      console.error("Failed to update post:", error);
      alert("Error updating post.");
    }
  };

  if (!initialData) {
    return <p>Loading post for editing...</p>;
  }

  return (
    <div>
      <h1>Edit Post</h1>
      <PostForm onSubmit={handleUpdatePost} initialData={initialData} />
    </div>
  );
};

export default UpdatePostPage;
