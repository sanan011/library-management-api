package com.devjoint.library.service;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import java.util.List;

public interface BookService {
    BookResponseDto createBook(BookRequestDto requestDto);
    BookResponseDto getBookById(Long id);
    List<BookResponseDto> getAllBooks();
    BookResponseDto updateBook(Long id, BookRequestDto requestDto);
    void deleteBook(Long id);
}
