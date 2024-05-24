package com.study.springstudy.springmvc.chap05.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/rest")
public class RestController {
    // 서버사이드 렌더링
    @GetMapping("/rest/hello")
    @ResponseBody // 서버가 클라이언트에게 순수한 데이터를 전달할 때 사용
    public String hello() {
        //...
        return "안녕안녕";
    }

    // 취미 배열에 담아 보내기
    @GetMapping("/hobby")
    @ResponseBody
    public List<String> hobby() {
        List<String> hobbies = List.of("태권도", "댄스", "장기");
        return hobbies;
    }

}
