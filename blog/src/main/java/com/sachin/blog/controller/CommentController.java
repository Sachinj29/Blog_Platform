package com.sachin.blog.controller;

import com.sachin.blog.dto.CommentDto;
import com.sachin.blog.exception.ResourceNotFoundException;
import com.sachin.blog.model.Comment;
import com.sachin.blog.model.Post;
import com.sachin.blog.model.User;
import com.sachin.blog.repository.CommentRepository;
import com.sachin.blog.repository.PostRepository;
import com.sachin.blog.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/posts/{postId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    // MODIFIED: Returns a list of DTOs now
    @GetMapping
    @Transactional(readOnly = true) // Important for lazy loading to work during conversion
    public List<CommentDto> getCommentsByPostId(@PathVariable Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // MODIFIED: Returns a DTO now
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<CommentDto> createComment(@PathVariable Long postId, @Valid @RequestBody CommentDto commentDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        User author = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        Comment comment = new Comment();
        comment.setContent(commentDto.getContent());
        comment.setPost(post);
        comment.setUser(author);

        Comment savedComment = commentRepository.save(comment);

        // Convert the saved entity to a DTO before sending the response
        return new ResponseEntity<>(convertToDto(savedComment), HttpStatus.CREATED);
    }

    // NEW: Private helper method to perform the conversion
    private CommentDto convertToDto(Comment comment) {
        CommentDto dto = new CommentDto();
        dto.setId(comment.getId());
        dto.setContent(comment.getContent());

        // Safely access the username from the lazy-loaded User object
        if (comment.getUser() != null) {
            dto.setAuthorUsername(comment.getUser().getUsername());
        }

        return dto;
    }
}
