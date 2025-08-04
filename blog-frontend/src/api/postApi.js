import api from './api.js';

export const getAllPosts = () => {
  return api.get('/posts');
};

export const getPostById = (id) => {
  return api.get(`/posts/${id}`);
};

export const createPost = (postData) => {
  return api.post('/posts', postData);
};

// --- NEW FUNCTIONS ---
export const updatePost = (id, postData) => {
  return api.put(`/posts/${id}`, postData);
};

export const deletePost = (id) => {
  return api.delete(`/posts/${id}`);
};
