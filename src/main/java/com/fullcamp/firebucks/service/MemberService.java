package com.fullcamp.firebucks.service;

import com.fullcamp.firebucks.domain.Member;
import com.fullcamp.firebucks.dto.MemberDTO;
import com.fullcamp.firebucks.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     * @param
     * @return Long
     */
    @Transactional
    public Long join(MemberDTO memberDTO) {
        Member entity = dtoToEntity(memberDTO);
        validateDuplicateMember(entity);
        memberRepository.save(entity);
        return entity.getId();
    }

    /**
     * 회원 전체 조회
     * @return list
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /**
     * 회원 단권 조
     * @param id
     * @return
     */
    public Member findOne(Long id) {
        return memberRepository.findOne(id);
    }

    @Transactional
    public void updateMember(MemberDTO memberDTO) {
        Member member = memberRepository.findOne(memberDTO.getId());
        member.updateMember(memberDTO);
    }


    // 테스트 완료
    private Member dtoToEntity(MemberDTO dto) {
        Member member = Member.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .password(dto.getPassword())
                .email(dto.getEmail())
                .id(dto.getId())
                .build();
        return member;
    }

    /**
     * 중복 메일 검증
     * @param member
     */
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByEmail(member.getEmail());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }




}
