package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        repository.clearMember();
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("Kim MinJeong");
        repository.save(member);

        Member checkMember = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(checkMember);
    }


    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("LeeGana");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("ParkDara");
        repository.save(member2);

        Member checkMember = repository.findByName("LeeGana").get();
        assertThat(checkMember).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Lee gana");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Park dara");
        repository.save(member2);

        List<Member> memberList = repository.findAll();

        assertThat(memberList.size()).isEqualTo(2);
    }
}
