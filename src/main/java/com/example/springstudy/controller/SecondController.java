package com.example.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecondController {
    @GetMapping("/random-quote")
    public String randomQuote(Model model) {
        String[] quotes = {
                "여자와 버스는 기다리면 온다" +
                        "-주상건-",
                "무대 위 춤을 추는 D선상의 아리야 불협화음도 괜찮아 뭐 문제가 될려나~" +
                        "-QWER discord-",
                "쏟아지는 맘을 멈출수가 없을까 너의 작은 인사 한마디에 요란해져서" +
                        "-QWER 고민중독-",
                "Oh baby please don't go~ 내 곁에 있어줘" +
                        "-트랜스픽션 너를원해-",
                "소중했던 사람이여 이젠 안녕~(그래그래 이젠 안녕)" +
                        "-싸이 뜨거운안녕-",
                "Obey your master~come crawlling master~" +
                        "-메탈리카 마오펫-"
        };
        int randInt = (int) (Math.random() * quotes.length);
        model.addAttribute("randomQuote", quotes[randInt]);
        return "quote";
    }
}
