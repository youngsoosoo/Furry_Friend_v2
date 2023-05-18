package com.friend.furry.basket.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.friend.furry.basket.dto.BasketRequestDTO;
import com.friend.furry.basket.dto.BasketResponseDTO;
import com.friend.furry.basket.service.BasketService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequiredArgsConstructor
@Log4j2
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/basket")
    public void basket(HttpSession session, Model model){
        BasketResponseDTO basketResponseDTO = basketService.findBasketList((long) session.getAttribute("mid"));
        try {
            log.warn(basketResponseDTO);
            model.addAttribute("li", basketResponseDTO);
        }catch (NullPointerException e){
            log.error(e);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/basket")
    public String basketSave(@RequestParam(value = "pname") String pname, HttpSession session){

        basketService.saveBasket(pname, (long) session.getAttribute("mid"));

        return "redirect:/basket/basket";
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/basket")
    public ResponseEntity<String> basketDelete(@RequestParam(value = "bid") Long bid, HttpSession session) {

        try {
            BasketRequestDTO basketRequestDTO = BasketRequestDTO.builder()
                .bid(bid)
                .build();
            basketService.deleteBasketItem(basketRequestDTO, (Long) session.getAttribute("mid"));

            String redirectUrl = "/basket/basket"; // 리다이렉트할 URL
            return ResponseEntity.ok().body(redirectUrl);
        } catch (Exception e) {
            // 실패했을 때의 동작을 구현
            log.error("삭제 실패");
            return ResponseEntity.badRequest().build(); // 실패 응답
        }
    }
}
