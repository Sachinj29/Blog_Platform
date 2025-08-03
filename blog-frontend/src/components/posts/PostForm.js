import React, { useState } from 'react';

const PostForm = ({ onSubmit, initialData = {} }) => {
  const [title, setTitle] = useState(initialData.title || '');
  const [content, setContent] = useState(initialData.content || '');
  const [categoryId, setCategoryId] = useState(initialData.categoryId || 1); // Default to "General"

  const handleSubmit = (e) => {
    e.preventDefault();
    onSubmit({ title, content, categoryId, tags: ["react", "frontend"] }); // Tags are hardcoded for now
  };

  return (
    <form onSubmit={handleSubmit}>
      <div>
        <label>Title</label>
        <input
          type="text"
          value={title}
          onChange={(e) => setTitle(e.target.value)}
          required
          style={{ width: '100%', padding: '8px', marginBottom: '1rem' }}
        />
      </div>
      <div>
        <label>Content</label>
        <textarea
          value={content}
          onChange={(e) => setContent(e.target.value)}
          required
          style={{ width: '100%', height: '200px', padding: '8px', marginBottom: '1rem' }}
        />
      </div>
      <button type="submit">Submit Post</button>
    </form>
  );
};

export default PostForm;
