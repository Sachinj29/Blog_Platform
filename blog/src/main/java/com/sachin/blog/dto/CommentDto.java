package com.sachin.blog.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CommentDto {
    private Long id;

    @NotEmpty
    @Size(min = 2, message = "Comment content must not be empty")
    private String content;

    private String authorUsername;
}
