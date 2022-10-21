package com.toyprj.start.controller;

import com.toyprj.start.service.BuyService;
import com.toyprj.start.service.ShopService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequiredArgsConstructor
public class BuyController {

    private final ShopService shopService;
    private final UserService userService;
    private final BuyService buyService;

    @PostMapping("/shop/buy/{shopNumber}")
    public String ShopBuy(@PathVariable("shopNumber")Long shopNumber,
                          @RequestParam("shopAmount")Long shopAmount,Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        int number = buyService.buyShop(shopService.getShop(shopNumber), userService.getUser(name).getId(), shopAmount);

        return "redirect:/shop/main";
    }

}
