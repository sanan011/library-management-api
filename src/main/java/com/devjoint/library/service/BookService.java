package com.devjoint.library.service;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDto createBook(BookRequestDto requestDto);
    BookResponseDto getBookById(Long id);
    Page<BookResponseDto> getAllBooks(Pageable pageable);
    BookResponseDto updateBook(Long id, BookRequestDto requestDto);
    void deleteBook(Long id);
}
