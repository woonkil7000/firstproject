package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // json을 반환하는 콘트롤러
public class FirstApiController {

    @GetMapping("/api/hello")
    public String hello() {

        return "hello, world!";

    }
}
