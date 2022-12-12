package com.xun.warmup;

import com.xun.warmup.repository.JdbcTempMemberRepository;
import com.xun.warmup.repository.JpaMemberRepository;
import com.xun.warmup.repository.MemberRepository;
import com.xun.warmup.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final EntityManager em;

    public SpringConfig(EntityManager em){
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }


    @Bean
    public MemberRepository memberRepository(){
        // return new MemoryMemberRepository();
        //return new JdbcTempMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }

}
