package com.example.firstproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {

    @GetMapping("/hi")
    public String niceToMeetYou(Model model){

        // 파라미터 사용을 위해 Model 사용한다.
        model.addAttribute("username","WoonKil");
        return "greetings"; //Templates/greetings.mustache -> 브라우저로 전송.
    }

    @GetMapping("/bye")
    public String seeYouNext(Model model){

        model.addAttribute("nickname","WoonKil");
        return "goodbye";
    }
//    @GetMapping("/articles/new")
//    public String newArticle(Model model){
//
//        model.addAttribute("nickname","홍길동");
//        return "articles/new";
//    }
}
