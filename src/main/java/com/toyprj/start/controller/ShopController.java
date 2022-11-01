package com.toyprj.start.controller;

import com.toyprj.start.entity.Shop;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardPage;
import com.toyprj.start.model.shopPage;
import com.toyprj.start.recode.SetModelName;
import com.toyprj.start.service.BoardService;
import com.toyprj.start.service.ShopService;
import com.toyprj.start.service.TodoService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;


    @GetMapping("/shop/main")
    public String getBoardListPage(Model model, @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageableMember) {

        SetModelName setModelName = new SetModelName(model);


        if(userService.getUser(setModelName.getName()).getRoles().equals("ROLE_MEMBER")) {
            model.addAttribute("shop", shopService.getShopList(pageableMember));

            List<shopPage> pageNum = new ArrayList<shopPage>();
            for (int i = 0;
                 i < (shopService.getShopPage() / 8) + 1 + (shopService.getShopPage() % 8 == 0 ? -1 : 0); i++) {
                shopPage sp = new shopPage(i, i + 1);
                pageNum.add(sp);
            }
            model.addAttribute("page", pageNum);

            if (userService.getUser(setModelName.getName()).getRoles().equals("ROLE_MANAGER")) {
                model.addAttribute("check", 1);
            }

            return "/shop/main";

        }else{
            model.addAttribute("shop", shopService.getSellShopList(userService.getUser(setModelName.getName()).getId()));

            return "/shop/sell";
        }
    }

    @GetMapping("/shop/sell")
    public String sell(Model model){

        SetModelName setModelName = new SetModelName(model);

        model.addAttribute("check", 1);
        model.addAttribute("shop", shopService.getSellShopList(userService.getUser(setModelName.getName()).getId()));

        return "/shop/sell";
    }
    @GetMapping("/shop/upload")
    public String FileUploadGet(Model model) {

        SetModelName setModelName = new SetModelName(model);

        return "/shop/upload";
    }

    @PostMapping("/shop/upload")
    public String FileUploadPost(@RequestParam("file") MultipartFile file,
                                 @RequestParam String shopTitle,
                                 @RequestParam String shopContent,
                                 @RequestParam String shopAmount,
                                 @RequestParam String shopPrice) throws IOException {

        SetModelName setModelName = new SetModelName();

        // 회원 정보 검색
        User user = userService.getUser(setModelName.getName());

        shopService.createShop(user, file, shopTitle, shopContent, shopAmount, shopPrice);

        return "redirect:/shop/sell";
    }

    @GetMapping("/shop/getShop/{shopNumber}")
    public String getShop(Model model, @PathVariable("shopNumber") Long shopNumber) {

        SetModelName setModelName = new SetModelName(model);

        Shop shop = shopService.getShop(shopNumber);

        Long id = userService.getUser(setModelName.getName()).getId();

        if (id == shop.getId()) {
            model.addAttribute("check", 1);
        }
        model.addAttribute("shop", shop);

        return "/shop/getShop";
    }

    @GetMapping("/shop/modifyShop/{shopNumber}")
    public String modifyShop(@PathVariable("shopNumber") Long shopNumber, Model model) {



        Shop shop = shopService.getShop(shopNumber);

        SetModelName setModelName = new SetModelName(model);

        model.addAttribute("shop", shop);

        return "/shop/modifyShop";
    }

    @PostMapping("/shop/modifyShop/{shopNumber}")
    public String modifyShop(@PathVariable("shopNumber") Long shopNumber,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam String shopTitle,
                             @RequestParam String shopContent,
                             @RequestParam Long shopPrice,
                             @RequestParam Long shopAmount,
                             Model model) throws IOException {

        SetModelName setModelName = new SetModelName(model);

        Shop shop = shopService.getShop(shopNumber);

        shopService.modifyShop(shop, shopTitle, shopContent,shopPrice, shopAmount, file);

        return "redirect:/shop/sell";
    }

    @GetMapping("/shop/deleteShop/{shopNumber}")
    public String deleteShop(@PathVariable("shopNumber") Long shopNumber, Model model) {

        SetModelName setModelName = new SetModelName(model);

        model.addAttribute("shopNumber", shopNumber);

        return "/shop/deleteShopProc";
    }

    @GetMapping("/shop/deleteShopProc/{shopNumber}")
    public String deleteShopProc(@PathVariable("shopNumber") Long shopNumber) {

        shopService.deleteShop(shopNumber);

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/myShop")
    public String myShop(Model model) {

        SetModelName setModelName = new SetModelName(model);

        List<Shop> shop = shopService.getMyShop(setModelName.getName());

        model.addAttribute("shop", shop);

        return "/shop/myShop";
    }

    @GetMapping("/shop/addMyShop/{shopNumber}")
    public String addMyShop(Model model, @PathVariable("shopNumber") Long shopNumber) {

        SetModelName setModelName = new SetModelName(model);

        userService.addMyShop(shopNumber, setModelName.getName());

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/deleteMyShop/{shopNumber}")
    public String deleteMyShop(Model model, @PathVariable("shopNumber") Long shopNumber) {

        SetModelName setModelName = new SetModelName(model);

        userService.deleteMyShop(shopNumber, setModelName.getName());

        return "redirect:/shop/myShop";
    }




}
