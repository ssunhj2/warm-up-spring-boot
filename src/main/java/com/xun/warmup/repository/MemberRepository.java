package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member); // 회원저장
    Optional<Member> findBySeqId(Long seqId); // seq id로 회원조회
    Optional<Member> findById(String id); // id로 회원조회
    Optional<Member> findByName(String name); // name으로 회원조회
    List<Member> findAll(); // 회원 전체목록 조회
}
