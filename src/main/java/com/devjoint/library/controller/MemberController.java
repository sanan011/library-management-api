package com.devjoint.library.controller;

import com.devjoint.library.dto.MemberRequestDto;
import com.devjoint.library.dto.MemberResponseDto;
import com.devjoint.library.service.MemberService;
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

@Tag(name = "Members", description = "Member Management API")
@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(summary = "Create a new member", description = "Adds a new member to the library")
    @ApiResponse(responseCode = "201", description = "Member created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @PostMapping
    public ResponseEntity<MemberResponseDto> createMember(@Valid @RequestBody MemberRequestDto requestDto) {
        MemberResponseDto createdMember = memberService.createMember(requestDto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdMember.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdMember);
    }

    @Operation(summary = "Get a member by ID", description = "Returns a single member by their ID")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @ApiResponse(responseCode = "404", description = "Member not found")
    @GetMapping("/{id}")
    public ResponseEntity<MemberResponseDto> getMemberById(@PathVariable Long id) {
        return ResponseEntity.ok(memberService.getMemberById(id));
    }

    @Operation(summary = "Get all members", description = "Returns a list of all members")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    @GetMapping
    public ResponseEntity<List<MemberResponseDto>> getAllMembers() {
        return ResponseEntity.ok(memberService.getAllMembers());
    }

    @Operation(summary = "Update an existing member", description = "Updates a member by their ID")
    @ApiResponse(responseCode = "200", description = "Member updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid input data")
    @ApiResponse(responseCode = "404", description = "Member not found")
    @PutMapping("/{id}")
    public ResponseEntity<MemberResponseDto> updateMember(@PathVariable Long id, @Valid @RequestBody MemberRequestDto requestDto) {
        return ResponseEntity.ok(memberService.updateMember(id, requestDto));
    }

    @Operation(summary = "Delete a member", description = "Deletes a member by their ID")
    @ApiResponse(responseCode = "204", description = "Member deleted successfully")
    @ApiResponse(responseCode = "404", description = "Member not found")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return ResponseEntity.noContent().build();
    }
}
