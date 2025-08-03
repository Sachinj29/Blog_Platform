import React from 'react';
import { Link } from 'react-router-dom';
import './PostCard.css'; // Import the CSS file

const PostCard = ({ post }) => {
  return (
    <div className="post-card">
      <h2><Link to={`/posts/${post.id}`}>{post.title}</Link></h2>
      <p className="post-meta">By {post.authorUsername} in <i>{post.categoryName}</i></p>
      <p>{post.content.substring(0, 150)}...</p>
    </div>
  );
};

export default PostCard;
