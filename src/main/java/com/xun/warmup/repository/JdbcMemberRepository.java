package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

/**
 * JDBC Repository
 */
public class JdbcMemberRepository implements MemberRepository{

    private final DataSource dataSource;

    public JdbcMemberRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        return null;
    }

    @Override
    public Optional<Member> findBySeqId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findById(String name) {
        return Optional.empty();
    }

    @Override
    public Optional<Member> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Member> findAll() {
        return null;
    }
}
