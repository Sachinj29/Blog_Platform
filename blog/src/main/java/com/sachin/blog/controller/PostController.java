package com.sachin.blog.controller;

import com.sachin.blog.dto.PostRequestDto;
import com.sachin.blog.dto.PostResponseDto;
import com.sachin.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") // <-- ADD THIS ANNOTATION
@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // NEW: Endpoint to get a single post
    @GetMapping("/{id}")
    public ResponseEntity<PostResponseDto> getPostById(@PathVariable Long id) {
        PostResponseDto postDto = postService.getPostById(id);
        return ResponseEntity.ok(postDto);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto createdPostDto = postService.createPost(postRequestDto);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    // NEW: Endpoint to update a post
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostResponseDto> updatePost(@PathVariable Long id, @Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto updatedPostDto = postService.updatePost(id, postRequestDto);
        return ResponseEntity.ok(updatedPostDto);
    }

    // NEW: Endpoint to delete a post
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Void> deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build(); // Standard response for successful delete
    }
}
