package com.sachin.blog.controller;

import com.sachin.blog.dto.PostRequestDto;
import com.sachin.blog.dto.PostResponseDto; // <-- Import the new DTO
// import com.sachin.blog.model.Post; // You can remove this import
import com.sachin.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // MODIFICATION 1: Change the return type here
    @GetMapping
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    // MODIFICATION 2: Change the return type here
    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<PostResponseDto> createPost(@Valid @RequestBody PostRequestDto postRequestDto) {
        PostResponseDto createdPostDto = postService.createPost(postRequestDto);
        return new ResponseEntity<>(createdPostDto, HttpStatus.CREATED);
    }

    // You would do the same for getById, update, etc.
}
