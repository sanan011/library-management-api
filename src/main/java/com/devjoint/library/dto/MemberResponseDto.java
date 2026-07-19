package com.devjoint.library.dto;

import lombok.Data;

@Data
public class MemberResponseDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
