package com.devjoint.library.mapper;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import com.devjoint.library.entity.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorRequestDto requestDto);
    AuthorResponseDto toResponseDto(Author author);
}
