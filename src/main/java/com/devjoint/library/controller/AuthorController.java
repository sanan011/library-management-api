package com.devjoint.library.controller;

import com.devjoint.library.dto.AuthorRequestDto;
import com.devjoint.library.dto.AuthorResponseDto;
import com.devjoint.library.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Authors", description = "Author Management API")
@RestController
@RequestMapping("/api/v1/authors")
@RequiredArgsConstructor
public class AuthorController {

    private final AuthorService authorService;

    @Operation(summary = "Create a new author", description = "Adds a new author to the library")
    @ApiResponse(responseCode = "201", description = "Author created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
        AuthorResponseDto createdAuthor = authorService.createAuthor(requestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdAuthor.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdAuthor);
    }

    @Operation(summary = "Get an author by ID", description = "Returns a single author by their ID")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Author not found")
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
        return ResponseEntity.ok(authorService.getAuthorById(id));
    }

    @Operation(summary = "Get all authors", description = "Returns a list of all authors")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public ResponseEntity<List<AuthorResponseDto>> getAllAuthors() {
        return ResponseEntity.ok(authorService.getAllAuthors());
    }

    @Operation(summary = "Update an existing author", description = "Updates an author by their ID")
    @ApiResponse(responseCode = "200", description = "Author updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Author not found")
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(@PathVariable Long id, @Valid @RequestBody AuthorRequestDto requestDto) {
        return ResponseEntity.ok(authorService.updateAuthor(id, requestDto));
    }

    @Operation(summary = "Delete an author", description = "Deletes an author by their ID")
    @ApiResponse(responseCode = "204", description = "Author deleted successfully")
    @ApiResponse(responseCode = "404", description = "Author not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
