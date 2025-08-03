import React from 'react';
import { Link } from 'react-router-dom';

const PostCard = ({ post }) => {
  const cardStyle = {
    border: '1px solid #ddd',
    padding: '1rem',
    marginBottom: '1rem',
    borderRadius: '8px'
  };

  return (
    <div style={cardStyle}>
      <h2><Link to={`/posts/${post.id}`}>{post.title}</Link></h2>
      <p>By {post.authorUsername} in <i>{post.categoryName}</i></p>
      <p>{post.content.substring(0, 150)}...</p>
    </div>
  );
};

export default PostCard;
