package com.devjoint.library.mapper;

import com.devjoint.library.dto.MemberRequestDto;
import com.devjoint.library.dto.MemberResponseDto;
import com.devjoint.library.entity.Member;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MemberMapper {
    Member toEntity(MemberRequestDto requestDto);
    MemberResponseDto toResponseDto(Member member);
}
