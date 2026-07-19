package com.devjoint.library.service.impl;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import com.devjoint.library.entity.Author;
import com.devjoint.library.mapper.AuthorMapper;
import com.devjoint.library.repository.AuthorRepository;
import com.devjoint.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public AuthorResponseDto createAuthor(AuthorRequestDto requestDto) {
        Author author = authorMapper.toEntity(requestDto);
        Author savedAuthor = authorRepository.save(author);
        return authorMapper.toResponseDto(savedAuthor);
    }

    @Override
    public AuthorResponseDto getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        return authorMapper.toResponseDto(author);
    }

    @Override
    public List<AuthorResponseDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(authorMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public AuthorResponseDto updateAuthor(Long id, AuthorRequestDto requestDto) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Author not found with id: " + id));
        
        existingAuthor.setName(requestDto.getName());
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return authorMapper.toResponseDto(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new RuntimeException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}
