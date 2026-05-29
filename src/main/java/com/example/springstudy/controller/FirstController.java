package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller  // 컨트롤러 선언
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model) {
        // model 객체가 "가원" 값을 "username"에 연결해 웹 브라우저로 보냄
        model.addAttribute("username", "가원");
        return "greetings";  // greetings.mustache 파일 반환
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model) {
        model.addAttribute("nickname", "가가원");
        return "goodbye";
    }
}
