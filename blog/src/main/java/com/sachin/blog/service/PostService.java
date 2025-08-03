package com.sachin.blog.service;

import com.sachin.blog.dto.PostRequestDto;
import com.sachin.blog.dto.PostResponseDto;
import com.sachin.blog.exception.ResourceNotFoundException;
import com.sachin.blog.exception.UnauthorizedException; // <-- NEW IMPORT
import com.sachin.blog.model.*;
import com.sachin.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;

    @Transactional(readOnly = true)
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // NEW: Get a single post by its ID
    @Transactional(readOnly = true)
    public PostResponseDto getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return convertToDto(post);
    }

    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequest) {
        User author = getCurrentUser();

        Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequest.getCategoryId()));

        Set<Tag> postTags = findOrCreateTags(postRequest.getTags());

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(author);
        post.setCategory(category);
        post.setTags(postTags);

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost);
    }

    // NEW: Logic to update a post
    @Transactional
    public PostResponseDto updatePost(Long id, PostRequestDto postRequest) {
        Post postToUpdate = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        checkAuthorization(postToUpdate);

        Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequest.getCategoryId()));

        Set<Tag> postTags = findOrCreateTags(postRequest.getTags());

        postToUpdate.setTitle(postRequest.getTitle());
        postToUpdate.setContent(postRequest.getContent());
        postToUpdate.setCategory(category);
        postToUpdate.getTags().clear();
        postToUpdate.getTags().addAll(postTags);

        Post updatedPost = postRepository.save(postToUpdate);
        return convertToDto(updatedPost);
    }

    // NEW: Logic to delete a post
    @Transactional
    public void deletePost(Long id) {
        Post postToDelete = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));

        checkAuthorization(postToDelete);

        postRepository.delete(postToDelete);
    }

    // --- Helper Methods ---

    private PostResponseDto convertToDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());

        if (post.getAuthor() != null) {
            dto.setAuthorUsername(post.getAuthor().getUsername());
        }
        if (post.getCategory() != null) {
            dto.setCategoryName(post.getCategory().getName());
        }
        if (post.getTags() != null) {
            dto.setTags(post.getTags().stream().map(Tag::getName).collect(Collectors.toSet()));
        }

        return dto;
    }

    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));
    }

    private Set<Tag> findOrCreateTags(Set<String> tagNames) {
        Set<Tag> tags = new HashSet<>();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(tagName);
                            return tagRepository.save(newTag);
                        });
                tags.add(tag);
            }
        }
        return tags;
    }

    // NEW: Authorization helper method
    private void checkAuthorization(Post post) {
        User currentUser = getCurrentUser();
        boolean isAdmin = currentUser.getRoles().stream()
                .anyMatch(role -> role.getName().equals("ROLE_ADMIN"));

        if (!post.getAuthor().getUsername().equals(currentUser.getUsername()) && !isAdmin) {
            throw new UnauthorizedException("You are not authorized to modify this post.");
        }
    }
}
