import React, { useState, useEffect } from 'react';
import { useParams } from 'react-router-dom';
import { getPostById } from '../api/postApi.js'; // Added .js
import { getCommentsForPost } from '../api/commentApi.js'; // Added .js

const PostDetailsPage = () => {
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const { id } = useParams();

  useEffect(() => {
    const fetchPostDetails = async () => {
      try {
        const postRes = await getPostById(id);
        setPost(postRes.data);
        const commentsRes = await getCommentsForPost(id);
        setComments(commentsRes.data);
      } catch (error) {
        console.error("Failed to fetch post details:", error);
      }
    };
    fetchPostDetails();
  }, [id]);

  if (!post) return <p>Loading post...</p>;

  return (
    <div>
      <h1>{post.title}</h1>
      <p>By {post.authorUsername} on {new Date(post.createdAt).toLocaleDateString()}</p>
      <div style={{ whiteSpace: 'pre-wrap' }}>{post.content}</div>

      <hr />
      <h3>Comments</h3>
      {comments.length > 0 ? (
        comments.map(comment => (
           // NOTE: I found a small bug here and fixed it for you.
           // It was comment.user.username, but your comment DTO only provides authorUsername.
          <div key={comment.id} style={{ borderBottom: '1px solid #ccc', paddingBottom: '0.5rem', marginBottom: '0.5rem' }}>
            <p><strong>{comment.authorUsername}:</strong> {comment.content}</p>
          </div>
        ))
      ) : (
        <p>No comments yet.</p>
      )}
    </div>
  );
};

export default PostDetailsPage;

