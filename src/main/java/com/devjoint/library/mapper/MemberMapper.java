package com.devjoint.library.mapper;

import com.devjoint.library.dto.MemberRequestDto;
import com.devjoint.library.dto.MemberResponseDto;
import com.devjoint.library.entity.Member;
import org.springframework.stereotype.Component;

@Component
public class MemberMapper {

    public Member toEntity(MemberRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        Member member = new Member();
        member.setFirstName(requestDto.getFirstName());
        member.setLastName(requestDto.getLastName());
        member.setEmail(requestDto.getEmail());
        return member;
    }

    public MemberResponseDto toResponseDto(Member member) {
        if (member == null) {
            return null;
        }
        MemberResponseDto dto = new MemberResponseDto();
        dto.setId(member.getId());
        dto.setFirstName(member.getFirstName());
        dto.setLastName(member.getLastName());
        dto.setEmail(member.getEmail());
        return dto;
    }
}
