package com.devjoint.library.service.impl;

import com.devjoint.library.dto.BookRequestDto;
import com.devjoint.library.dto.BookResponseDto;
import com.devjoint.library.entity.Author;
import com.devjoint.library.entity.Book;
import com.devjoint.library.mapper.BookMapper;
import com.devjoint.library.repository.AuthorRepository;
import com.devjoint.library.repository.BookRepository;
import com.devjoint.library.service.BookService;
import com.devjoint.library.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDto createBook(BookRequestDto requestDto) {
        Book book = bookMapper.toEntity(requestDto);
        
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + requestDto.getAuthorId()));
        
        book.setAuthor(author);
        Book savedBook = bookRepository.save(book);
        
        return bookMapper.toResponseDto(savedBook);
    }

    @Override
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return bookMapper.toResponseDto(book);
    }

    @Override
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookResponseDto updateBook(Long id, BookRequestDto requestDto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        
        existingBook.setTitle(requestDto.getTitle());
        existingBook.setIsbn(requestDto.getIsbn());
        
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + requestDto.getAuthorId()));
        
        existingBook.setAuthor(author);
        
        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toResponseDto(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}
