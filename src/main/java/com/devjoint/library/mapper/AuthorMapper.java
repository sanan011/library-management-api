package com.devjoint.library.mapper;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import com.devjoint.library.entity.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public Author toEntity(AuthorRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Author author = new Author();
        author.setName(requestDto.getName());
        return author;
    }

    public AuthorResponseDto toResponseDto(Author author) {
        if (author == null) {
            return null;
        }
        AuthorResponseDto dto = new AuthorResponseDto();
        dto.setId(author.getId());
        dto.setName(author.getName());
        return dto;
    }
}
