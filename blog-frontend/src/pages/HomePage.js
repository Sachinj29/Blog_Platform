import React, { useState, useEffect } from 'react';
import { getAllPosts } from '../api/postApi';
import PostCard from '../components/posts/PostCard';

const HomePage = () => {
  const [posts, setPosts] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchPosts = async () => {
      try {
        const response = await getAllPosts();
        setPosts(response.data);
      } catch (error) {
        console.error("Failed to fetch posts:", error);
      } finally {
        setLoading(false);
      }
    };
    fetchPosts();
  }, []);

  if (loading) return <p>Loading posts...</p>;

  return (
    <div>
      <h1>Blog Posts</h1>
      {posts.length > 0 ? (
        posts.map(post => <PostCard key={post.id} post={post} />)
      ) : (
        <p>No posts yet. Be the first to create one!</p>
      )}
    </div>
  );
};

export default HomePage;
