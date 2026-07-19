package com.devjoint.library.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MemberRequestDto {
    @NotBlank(message = "First name is mandatory")
    private String firstName;
    
    @NotBlank(message = "Last name is mandatory")
    private String lastName;
    
    @NotBlank(message = "Email is mandatory")
    @Email(message = "Email should be valid")
    private String email;
}
