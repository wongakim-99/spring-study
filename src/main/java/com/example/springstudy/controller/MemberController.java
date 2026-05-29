package com.example.springstudy.controller;

import com.example.springstudy.dto.MemberForm;
import com.example.springstudy.entity.Member;
import com.example.springstudy.repository.MemberRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Slf4j
@Controller
public class MemberController {
    @Autowired  // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private MemberRepository memberRepository;  // 객체 선언

    public MemberController(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @GetMapping("/members/new")
    public String newMemberForm() {
        return "members/new";
    }

    @PostMapping("/join")
    public String joinMember(MemberForm form) {
        log.info(form.toString());  // DTO에 폼 데이터가 잘 담겼는지 확인

        // 1. DTO를 엔티티로 변환
        Member member = (Member) form.toEntity();
        log.info(member.toString());  // DTO가 엔티티로 잘 변환되는지 확인 출력

        // 2. 리파지터리로 엔티티를 DB에 저장
        Member saved = memberRepository.save(member);
        log.info(saved.toString());
        return "";
    }

    @GetMapping("members/{id}")
    public String show(@PathVariable Long id, Model model) {
        log.info("id = "+id);  // id를 잘 받았는지 확인하는 로그 찍기

        // 1. id를 조회해 데이터 가져오기
        Member memberEntity = memberRepository.findById(id).orElse(null);
        // 2. 모델에 데이터 등록
        model.addAttribute("member", memberEntity);
        return "members/show";
    }

    @GetMapping("/members")
    public String index(Model model) {
        // 1. 모든 데이터 가져오기
        List<Member> memberEntityList = (List<Member>) memberRepository.findAll();

        // 2. 모델에 데이터 등록
        model.addAttribute("memberList", memberEntityList);

        // 3. 뷰 페이지 설정
        return "members/index";
    }
}
