package com.toyprj.start.controller;

import com.toyprj.start.entity.Buy;
import com.toyprj.start.model.BuyDto;
import com.toyprj.start.recode.SetModelName;
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

import java.util.ArrayList;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class BuyController {

    private final ShopService shopService;
    private final UserService userService;
    private final BuyService buyService;

    @PostMapping("/shop/buy/{shopNumber}")
    public String ShopBuy(@PathVariable("shopNumber")Long shopNumber,
                          @RequestParam("shopAmount")Long shopAmount,Model model){

        SetModelName setModelName = new SetModelName(model);

        int number = buyService.buyShop(shopService.getShop(shopNumber), userService.getUser(setModelName.getName()).getId(), shopAmount);

        model.addAttribute("number", number);

        return "/shop/buyCheck";
    }

    @GetMapping("/shop/sellCheck")
    public String sellCheck(Model model){

        SetModelName setModelName = new SetModelName(model);

        List<Buy> list = buyService.sellCheck(userService.getUser(setModelName.getName()).getId());
        List<BuyDto> check = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){

            check.add(new BuyDto(list.get(i).getShopNumber(),
                                shopService.getShop(list.get(i).getShopNumber()).getShopTitle(),
                                userService.getIdUser(list.get(i).getBuyId()).getUserName(),
                                  list.get(i).getBuyAmount()));
        }

        model.addAttribute("shop", check);

        return "/shop/sellCheck";
    }

    @GetMapping("/shop/buyCheckPage")
    public String buyCheck(Model model){

        SetModelName setModelName = new SetModelName(model);

        List<Buy> list = buyService.buyCheck(userService.getUser(setModelName.getName()).getId());
        List<BuyDto> check = new ArrayList<>();

        for(int i = 0; i < list.size(); i++){

            check.add(new BuyDto(list.get(i).getBuyAmount(),
                    list.get(i).getShopNumber(),
                    shopService.getShop(list.get(i).getShopNumber()).getShopTitle(),
                    userService.getIdUser(list.get(i).getSellId()).getUserName()));
        }

        model.addAttribute("shop", check);

        return "/shop/buyCheckPage";
    }
}
