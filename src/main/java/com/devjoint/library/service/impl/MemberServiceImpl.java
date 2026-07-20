package com.devjoint.library.service.impl;

import com.devjoint.library.dto.MemberRequestDto;
import com.devjoint.library.dto.MemberResponseDto;
import com.devjoint.library.entity.Member;
import com.devjoint.library.mapper.MemberMapper;
import com.devjoint.library.repository.MemberRepository;
import com.devjoint.library.service.MemberService;
import com.devjoint.library.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Override
    public MemberResponseDto createMember(MemberRequestDto requestDto) {
        Member member = memberMapper.toEntity(requestDto);
        Member savedMember = memberRepository.save(member);
        return memberMapper.toResponseDto(savedMember);
    }

    @Override
    public MemberResponseDto getMemberById(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        return memberMapper.toResponseDto(member);
    }

    @Override
    public List<MemberResponseDto> getAllMembers() {
        return memberRepository.findAll().stream()
                .map(memberMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public MemberResponseDto updateMember(Long id, MemberRequestDto requestDto) {
        Member existingMember = memberRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found with id: " + id));
        
        existingMember.setFirstName(requestDto.getFirstName());
        existingMember.setLastName(requestDto.getLastName());
        existingMember.setEmail(requestDto.getEmail());
        
        Member updatedMember = memberRepository.save(existingMember);
        return memberMapper.toResponseDto(updatedMember);
    }

    @Override
    public void deleteMember(Long id) {
        if (!memberRepository.existsById(id)) {
            throw new ResourceNotFoundException("Member not found with id: " + id);
        }
        memberRepository.deleteById(id);
    }
}
