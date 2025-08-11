package com.example.one_hour_project.controller;

import com.example.one_hour_project.dto.JoinRequest;
import com.example.one_hour_project.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/hello")
    public String hello() {
        return "hello, world";
    }

    @PostMapping("/join")
    public String join(@RequestBody JoinRequest joinRequest) {
        String id = joinRequest.getId();
        String name = joinRequest.getName();
        String phoneNumber = joinRequest.getPhoneNumber();

        String result = memberService.join(id, name, phoneNumber);
        if("success".equalsIgnoreCase(result)){
            return "join success";
        }else{
            return "join fail";
        }
    }

}
