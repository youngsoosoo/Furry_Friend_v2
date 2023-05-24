package com.friend.furry.basket.controller;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.friend.furry.basket.dto.BasketRequestDTO;
import com.friend.furry.basket.dto.BasketResponseDTO;
import com.friend.furry.basket.service.BasketService;
import com.friend.furry.member.service.TokenService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    private final TokenService tokenService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/basket")
    public void basket(Model model, HttpServletRequest request){
        BasketResponseDTO basketResponseDTO = basketService.findBasketList(Arrays.stream(request.getCookies()).filter(cookie -> "access_token".equals(cookie.getName())).findFirst().map(
            Cookie::getValue).orElse(null));
        try {
            log.warn(basketResponseDTO);
            model.addAttribute("li", basketResponseDTO);
        }catch (NullPointerException e){
            log.error(e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/basket")
    public String basketSave(@RequestParam(value = "pname") String pname, HttpServletRequest request){

        basketService.saveBasket(pname, Arrays.stream(request.getCookies()).filter(cookie -> "access_token".equals(cookie.getName())).findFirst().map(
            Cookie::getValue).orElse(null));

        return "redirect:/basket/basket";
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/basket")
    public ResponseEntity<String> basketDelete(@RequestParam(value = "bid") Long bid, HttpServletRequest request) {

        try {
            BasketRequestDTO basketRequestDTO = BasketRequestDTO.builder()
                .bid(bid)
                .build();
            basketService.deleteBasketItem(basketRequestDTO, Arrays.stream(request.getCookies()).filter(cookie -> "access_token".equals(cookie.getName())).findFirst().map(
                Cookie::getValue).orElse(null));

            String redirectUrl = "/basket/basket"; // 리다이렉트할 URL
            return ResponseEntity.ok().body(redirectUrl);
        } catch (Exception e) {
            // 실패했을 때의 동작을 구현
            log.error("삭제 실패");
            return ResponseEntity.badRequest().build(); // 실패 응답
        }
    }
}
