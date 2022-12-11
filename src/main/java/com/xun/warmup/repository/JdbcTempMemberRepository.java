package com.xun.warmup.repository;

import com.xun.warmup.domain.Member;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * JDBC Template Repository
 */
public class JdbcTempMemberRepository implements MemberRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTempMemberRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);

        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("SEQ_NO");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("ID", member.getId());
        parameters.put("NAME", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setSeqId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findBySeqId(Long seqId) {
        List<Member> memberList = jdbcTemplate.query("select * from member where SEQ_NO = ?", memberRowMapper(), seqId);
        return memberList.stream().findAny();
    }

    @Override
    public Optional<Member> findById(String id) {
        List<Member> memberList = jdbcTemplate.query("select * from member where ID = ?", memberRowMapper(), id);
        return memberList.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> memberList = jdbcTemplate.query("select * from member where NAME = ?", memberRowMapper(), name);
        return memberList.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select * from member", memberRowMapper());
    }

    // 반환된 값을 member 객체에 담아 반환한다.
    private RowMapper<Member> memberRowMapper(){
        return new RowMapper<Member>() {
            @Override
            public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
                Member member = new Member();
                member.setSeqId(rs.getLong("SEQ_NO"));
                member.setId(rs.getString("ID"));
                member.setName(rs.getString("NAME"));
                return member;
            }
        };

        /*
        * 위 코드를 lambda로 표현하면 이와 같음
        * return (rs, rowNum) -> {
            Member member = new Member();
            member.setSeqId(rs.getLong("SEQ_NO"));
            member.setId(rs.getString("ID"));
            member.setName(rs.getString("NAME"));
            return member;
        };
        * */
    }
}
