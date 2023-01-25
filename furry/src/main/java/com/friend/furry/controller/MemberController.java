package com.friend.furry.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
    @GetMapping("/login")
    //error은 로그인 실패했을 때의 파라미터
    //logout은 로그아웃 한 후 로그인으로 이동했을 때의 파라미터
    public void login(String logout){
        if(logout != null){
            log.info("로그아웃");
        }
    }
}