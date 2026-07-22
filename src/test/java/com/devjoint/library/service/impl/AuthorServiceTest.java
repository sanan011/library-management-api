package com.devjoint.library.service.impl;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import com.devjoint.library.entity.Author;
import com.devjoint.library.exception.ResourceNotFoundException;
import com.devjoint.library.mapper.AuthorMapper;
import com.devjoint.library.repository.AuthorRepository;
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
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @Mock
    private AuthorMapper authorMapper;

    @InjectMocks
    private AuthorServiceImpl authorService;

    @Test
    void createAuthor_SuccessfulScenario() {
        // Arrange
        AuthorRequestDto requestDto = new AuthorRequestDto();
        requestDto.setName("John Doe");

        Author author = new Author();
        author.setName("John Doe");

        Author savedAuthor = new Author();
        savedAuthor.setId(1L);
        savedAuthor.setName("John Doe");

        AuthorResponseDto responseDto = new AuthorResponseDto();
        responseDto.setId(1L);
        responseDto.setName("John Doe");

        when(authorMapper.toEntity(requestDto)).thenReturn(author);
        when(authorRepository.save(any(Author.class))).thenReturn(savedAuthor);
        when(authorMapper.toResponseDto(savedAuthor)).thenReturn(responseDto);

        // Act
        AuthorResponseDto result = authorService.createAuthor(requestDto);

        // Assert
        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("John Doe", result.getName());
        verify(authorMapper).toEntity(requestDto);
        verify(authorRepository).save(any(Author.class));
        verify(authorMapper).toResponseDto(savedAuthor);
    }

    @Test
    void getAuthorById_ThrowsResourceNotFoundException_WhenNotFound() {
        // Arrange
        Long authorId = 99L;
        when(authorRepository.findById(authorId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            authorService.getAuthorById(authorId);
        });

        assertEquals("Author not found with id: " + authorId, exception.getMessage());
        verify(authorRepository).findById(authorId);
        verifyNoInteractions(authorMapper);
    }

    @Test
    void deleteAuthor_SuccessfulScenario() {
        // Arrange
        Long authorId = 1L;
        when(authorRepository.existsById(authorId)).thenReturn(true);

        // Act
        authorService.deleteAuthor(authorId);

        // Assert
        verify(authorRepository).existsById(authorId);
        verify(authorRepository).deleteById(authorId);
    }
}
