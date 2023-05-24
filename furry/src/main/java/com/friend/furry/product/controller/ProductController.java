package com.friend.furry.product.controller;

import java.util.Arrays;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import com.friend.furry.member.service.TokenService;
import com.friend.furry.product.dto.PageRequestDTO;
import com.friend.furry.product.dto.PageResponseDTO;
import com.friend.furry.product.dto.ProductDTO;
import com.friend.furry.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    private final TokenService tokenService;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(ProductDTO productDTO, RedirectAttributes redirectAttributes, HttpServletRequest request){
        log.info("productDTO:" + productDTO);
        productDTO.setMid(tokenService.usageToken(Arrays.stream(request.getCookies()).filter(cookie -> "access_token".equals(cookie.getName())).findFirst().map(
            Cookie::getValue).orElse(null)));
        Long pid = productService.register(productDTO);
        redirectAttributes.addFlashAttribute("msg", pid + "삽입 성공");
        return "redirect:/product/list";
    }

    @GetMapping("/list")
    public void list(PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO pageResponseDTO = productService.getList(pageRequestDTO);
        model.addAttribute("result", pageResponseDTO);
    }
    @GetMapping("/read")
    public void read(Long pid, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model, HttpServletRequest request){
        ProductDTO productDTO = productService.getProduct(pid);
        model.addAttribute("dto", productDTO);
        model.addAttribute("mid", tokenService.usageToken(
            Arrays.stream(request.getCookies()).filter(cookie -> "access_token".equals(cookie.getName())).findFirst().map(
                Cookie::getValue).orElse(null)));
    }
}
