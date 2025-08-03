package com.sachin.blog.service;

import com.sachin.blog.dto.PostRequestDto;
import com.sachin.blog.dto.PostResponseDto; // <-- Add this import
import com.sachin.blog.exception.ResourceNotFoundException;
import com.sachin.blog.model.*;
import com.sachin.blog.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; // <-- Add this import

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors; // <-- Add this import

@Service
public class PostService {

    // ... (Your existing @Autowired fields are fine)
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private TagRepository tagRepository;


    // MODIFICATION 1: Change the return type of getAllPosts
    @Transactional(readOnly = true) // Important for lazy loading to work during conversion
    public List<PostResponseDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // MODIFICATION 2: Change the return type of createPost
    @Transactional
    public PostResponseDto createPost(PostRequestDto postRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Category category = categoryRepository.findById(postRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", postRequest.getCategoryId()));

        Set<Tag> postTags = new HashSet<>();
        if (postRequest.getTags() != null) {
            for (String tagName : postRequest.getTags()) {
                Tag tag = tagRepository.findByName(tagName)
                        .orElseGet(() -> {
                            Tag newTag = new Tag();
                            newTag.setName(tagName);
                            return tagRepository.save(newTag);
                        });
                postTags.add(tag);
            }
        }

        Post post = new Post();
        post.setTitle(postRequest.getTitle());
        post.setContent(postRequest.getContent());
        post.setAuthor(author);
        post.setCategory(category);
        post.setTags(postTags);

        Post savedPost = postRepository.save(post);
        return convertToDto(savedPost); // Convert to DTO before returning
    }

    // NEW METHOD: Add this private helper method to the class
    private PostResponseDto convertToDto(Post post) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setUpdatedAt(post.getUpdatedAt());

        // This is where lazy-loaded properties are safely accessed
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
}
