package com.devjoint.library.service.impl;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import com.devjoint.library.entity.Author;
import com.devjoint.library.entity.Book;
import com.devjoint.library.exception.ResourceNotFoundException;
import com.devjoint.library.mapper.BookMapper;
import com.devjoint.library.repository.AuthorRepository;
import com.devjoint.library.repository.BookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private BookMapper bookMapper;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void createBook_SuccessfulScenario() {
        // Arrange
        BookRequestDto requestDto = new BookRequestDto();
        requestDto.setTitle("Spring Boot Guide");
        requestDto.setIsbn("123456789");
        requestDto.setAuthorId(1L);

        Book book = new Book();
        book.setTitle("Spring Boot Guide");
        book.setIsbn("123456789");

        Author author = new Author();
        author.setId(1L);
        author.setName("John Doe");

        Book savedBook = new Book();
        savedBook.setId(1L);
        savedBook.setTitle("Spring Boot Guide");
        savedBook.setIsbn("123456789");
        savedBook.setAuthor(author);

        BookResponseDto responseDto = new BookResponseDto();
        responseDto.setId(1L);
        responseDto.setTitle("Spring Boot Guide");

        when(bookMapper.toEntity(requestDto)).thenReturn(book);
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(bookRepository.save(any(Book.class))).thenReturn(savedBook);
        when(bookMapper.toResponseDto(savedBook)).thenReturn(responseDto);

        // Act
        BookResponseDto result = bookService.createBook(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Spring Boot Guide", result.getTitle());
        verify(bookMapper).toEntity(requestDto);
        verify(authorRepository).findById(1L);
        verify(bookRepository).save(any(Book.class));
        verify(bookMapper).toResponseDto(savedBook);
    }

    @Test
    void getBookById_ThrowsResourceNotFoundException_WhenNotFound() {
        // Arrange
        Long bookId = 99L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            bookService.getBookById(bookId);
        });

        assertEquals("Book not found with id: " + bookId, exception.getMessage());
        verify(bookRepository).findById(bookId);
        verifyNoInteractions(bookMapper);
    }
}
