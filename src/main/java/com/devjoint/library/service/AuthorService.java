package com.devjoint.library.service;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import java.util.List;

public interface AuthorService {
    AuthorResponseDto createAuthor(AuthorRequestDto requestDto);
    AuthorResponseDto getAuthorById(Long id);
    List<AuthorResponseDto> getAllAuthors();
    AuthorResponseDto updateAuthor(Long id, AuthorRequestDto requestDto);
    void deleteAuthor(Long id);
}
