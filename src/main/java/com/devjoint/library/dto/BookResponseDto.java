package com.devjoint.library.dto;

import lombok.Data;

@Data
public class BookResponseDto {
    private Long id;
    private String title;
    private String isbn;
    private Long authorId;
    private String authorName;
}
