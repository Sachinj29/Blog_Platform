package com.sachin.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.util.Set;

@Data
public class PostRequestDto {
    @NotEmpty
    @Size(min = 5, message = "Post title must have at least 5 characters")
    private String title;

    @NotEmpty
    @Size(min = 10, message = "Post content must have at least 10 characters")
    private String content;

    @NotNull
    private Long categoryId;

    private Set<String> tags; // A set of tag names
}
