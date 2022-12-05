package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{
    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L; // id

    @Override
    public Member save(Member member) {
        member.setSeqId(++sequence);
        store.put(member.getSeqId(), member);
        return member;
    }

    @Override
    public Optional<Member> findBySeqId(Long seqId) {
        return Optional.ofNullable(store.get(seqId));
    }

    @Override
    public Optional<Member> findById(String id) {
        return store.values().stream()
                .filter(member -> member.getId().equals(id))
                .findAny();
    }
    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // 멤버 전체 초기화
    public void clearMember(){
        store.clear();
    }
}
