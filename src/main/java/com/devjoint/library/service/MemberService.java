package com.devjoint.library.service;

import com.devjoint.library.dto.MemberRequestDto;
import com.devjoint.library.dto.MemberResponseDto;
import java.util.List;

public interface MemberService {
    MemberResponseDto createMember(MemberRequestDto requestDto);
    MemberResponseDto getMemberById(Long id);
    List<MemberResponseDto> getAllMembers();
    MemberResponseDto updateMember(Long id, MemberRequestDto requestDto);
    void deleteMember(Long id);
}
