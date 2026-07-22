package com.devjoint.library.mapper;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import com.devjoint.library.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(target = "author", ignore = true)
    Book toEntity(BookRequestDto requestDto);

    @Mapping(source = "author.id", target = "authorId")
    @Mapping(source = "author.name", target = "authorName")
    BookResponseDto toResponseDto(Book book);
}
