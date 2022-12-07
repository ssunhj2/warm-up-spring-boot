package com.xun.warmup.controller;

import com.xun.warmup.domain.Member;
import com.xun.warmup.form.MemberForm;
import com.xun.warmup.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm memberForm){
        Member member = new Member();
        member.setId(memberForm.getId());
        member.setName(memberForm.getName());

        memberService.join(member); // 회원가입

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers(); // 멤버 전체조회
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
