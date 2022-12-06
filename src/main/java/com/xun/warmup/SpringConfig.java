package com.xun.warmup;

import com.xun.warmup.repository.MemberRepository;
import com.xun.warmup.repository.MemoryMemberRepository;
import com.xun.warmup.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
    
}
