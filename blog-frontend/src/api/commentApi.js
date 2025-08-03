import api from './api.js';

export const getCommentsForPost = (postId) => {
  return api.get(`/posts/${postId}/comments`);
};

export const createComment = (postId, commentData) => {
  return api.post(`/posts/${postId}/comments`, commentData);
};
