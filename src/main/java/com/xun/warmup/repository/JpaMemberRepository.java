package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em){
        this.em = em;
    }


    @Override
    public Member save(Member member) {
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findBySeqId(Long seqId) {
        Member member = em.find(Member.class, seqId);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findById(String id) {
        List<Member> members = em.createQuery("select m from Member m where id = :id", Member.class)
                .setParameter("id", id)
                .getResultList();

        return members.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> members = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return members.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
