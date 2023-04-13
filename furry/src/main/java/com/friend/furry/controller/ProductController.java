package com.friend.furry.controller;

import javax.servlet.http.HttpSession;

import com.friend.furry.security.dto.PageRequestDTO;
import com.friend.furry.security.dto.PageResponseDTO;
import com.friend.furry.security.dto.ProductDTO;
import com.friend.furry.service.ProductService;
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

    private final HttpSession session;

    @GetMapping("/register")
    public void register(){

    }

    @PostMapping("/register")
    public String register(ProductDTO productDTO, RedirectAttributes redirectAttributes){
        log.info("productDTO:" + productDTO);
        productDTO.setMid((Long)session.getAttribute("mid"));
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
    public void read(Long pid, @ModelAttribute("requestDTO") PageRequestDTO pageRequestDTO, Model model){
        ProductDTO productDTO = productService.getProduct(pid);
        model.addAttribute("dto", productDTO);
        model.addAttribute("mid", session.getAttribute("mid"));
    }
}
