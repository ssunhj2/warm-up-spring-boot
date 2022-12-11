package com.xun.warmup.service;

import com.xun.warmup.domain.Member;
import com.xun.warmup.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 여기서 테스트코드는 기록을 목적으로 commit
 * 스프링 통합 테스트
 */
@SpringBootTest
@Transactional
public class MemberServiceIntTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    void join() throws Exception {
        // 값
        Member member = new Member();
        member.setId("kimmj");
        member.setName("Kim MinJeong");

        // 로직
        Long saveSeqId = memberService.join(member);

        // 결과
        Member findMember = memberRepository.findBySeqId(saveSeqId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void exceptDupMemberId() throws Exception {
        // 값
        Member member1 = new Member();
        member1.setId("leegn");
        member1.setName("Lee Gana");

        Member member2 = new Member();
        member2.setId("leegn");
        member2.setName("Park Dara");

        // 로직
        memberService.join(member1);

        // join을 실행하면 IllegalStateException 예외가 발생하는지 테스트
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        // 위 코드는 아래와 같다
        /*
            try{
                memberService.join(member2);
            }catch(IllegalStateException e){
                Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
            }
        */

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }
}
