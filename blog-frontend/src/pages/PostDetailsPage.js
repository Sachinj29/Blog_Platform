import React, { useState, useEffect } from 'react';
import { useParams, useNavigate, Link } from 'react-router-dom';
import { getPostById, deletePost } from '../api/postApi.js';
import { getCommentsForPost } from '../api/commentApi.js';
import { useAuth } from '../hooks/useAuth.js';
import './PostDetailsPage.css'; // Import the new CSS file

const PostDetailsPage = () => {
  const [post, setPost] = useState(null);
  const [comments, setComments] = useState([]);
  const { id } = useParams();
  const navigate = useNavigate();
  const { currentUser } = useAuth();

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

  const handleDelete = async () => {
    if (window.confirm('Are you sure you want to delete this post?')) {
      try {
        await deletePost(id);
        navigate('/');
      } catch (error) {
        console.error("Failed to delete post:", error);
        alert("Error deleting post.");
      }
    }
  };

  if (!post) return <p>Loading post...</p>;

  const isAuthor = currentUser && currentUser.username === post.authorUsername;

  return (
    <div className="post-detail-container">
      <div className="post-header">
        <h1>{post.title}</h1>
        <p className="post-meta">
          By {post.authorUsername} on {new Date(post.createdAt).toLocaleDateString()}
        </p>
      </div>
      
      {isAuthor && (
        <div className="post-actions">
          <Link to={`/posts/${id}/edit`}>
            <button className="edit-button">Edit Post</button>
          </Link>
          <button onClick={handleDelete} className="delete-button">
            Delete Post
          </button>
        </div>
      )}

      <div className="post-content">
        {post.content}
      </div>

      <div className="comments-section">
        <h3>Comments</h3>
        {comments.length > 0 ? (
          comments.map(comment => (
            <div key={comment.id} className="comment-item">
              <p>
                <span className="comment-author">{comment.authorUsername}:</span>
                <span className="comment-content">{comment.content}</span>
              </p>
            </div>
          ))
        ) : (
          <p>No comments yet.</p>
        )}
      </div>
    </div>
  );
};

export default PostDetailsPage;
