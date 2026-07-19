package com.devjoint.library.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookRequestDto {
    @NotBlank(message = "Title is mandatory")
    private String title;
    
    @NotBlank(message = "ISBN is mandatory")
    private String isbn;
    
    @NotNull(message = "Author ID is mandatory")
    private Long authorId;
}
