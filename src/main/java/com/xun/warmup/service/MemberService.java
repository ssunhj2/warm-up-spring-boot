package com.xun.warmup.service;

import com.xun.warmup.domain.Member;
import com.xun.warmup.repository.MemberRepository;
import com.xun.warmup.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return Id
     */
    public Long join(Member member){
        validDupMemberId(member);
        memberRepository.save(member);
        return member.getSeqId();
    }

    /**
     * 사용자 아이디가 중복되는지 체크
     * @param member
     */
    private void validDupMemberId(Member member) {
        memberRepository.findById(member.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     * @return
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * Id로 회원 조회
     * @param memberId
     * @return
     */
    public Optional<Member> findMember(String memberId){
        return memberRepository.findById(memberId);
    }

}
