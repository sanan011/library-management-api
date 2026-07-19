package com.devjoint.library.mapper;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import com.devjoint.library.entity.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public Book toEntity(BookRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Book book = new Book();
        book.setTitle(requestDto.getTitle());
        book.setIsbn(requestDto.getIsbn());
        // Note: Author relationship is resolved in BookService
        return book;
    }

    public BookResponseDto toResponseDto(Book book) {
        if (book == null) {
            return null;
        }
        BookResponseDto dto = new BookResponseDto();
        dto.setId(book.getId());
        dto.setTitle(book.getTitle());
        dto.setIsbn(book.getIsbn());
        if (book.getAuthor() != null) {
            dto.setAuthorId(book.getAuthor().getId());
            dto.setAuthorName(book.getAuthor().getName());
        }
        return dto;
    }
}
