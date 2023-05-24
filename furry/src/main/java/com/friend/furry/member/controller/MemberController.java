package com.friend.furry.member.controller;

import com.friend.furry.member.dto.MemberJoinDTO;
import com.friend.furry.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/member")
//member와 관련된 요청을 처리할 메서드
public class MemberController {

    @GetMapping("/login")
    //logout은 로그아웃 한 후 로그인으로 이동했을 때의 파라미터
    public void login(String logout){
        if(logout != null){
            log.info("로그아웃");
        }
    }

    //로그인 한 유저만 접속이 가능
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/member")
    public void member(){
        log.info("멤버만 허용");
        //로그인 한 유저 정보
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("로그인 한 유저", authentication.getPrincipal());
    }

    private final MemberService memberService;

    //회원 가입 페이지로 이동
    @GetMapping("/join")
    public void join(){
        log.info("회원 가입 페이지로 이동");
    }
    //회원 가입 처리
    @PostMapping("/join")
    public String join(MemberJoinDTO memberJoinDTO, RedirectAttributes rattr){
        log.info(memberJoinDTO);
        try{
            memberService.join(memberJoinDTO);
            //성공
        }catch(Exception e){
            rattr.addFlashAttribute("error","mid");
            return "redirect:/member/join";
        }
        rattr.addFlashAttribute("result", "success");
        return "redirect:/member/login";
    }
}