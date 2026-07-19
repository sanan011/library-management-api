package com.devjoint.library.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AuthorRequestDto {
    @NotBlank(message = "Name is mandatory")
    private String name;
}
