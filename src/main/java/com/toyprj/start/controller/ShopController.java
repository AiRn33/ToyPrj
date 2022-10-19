package com.toyprj.start.controller;

import com.toyprj.start.entity.Shop;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardPage;
import com.toyprj.start.model.shopPage;
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
    public String getBoardListPage(Model model, @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        model.addAttribute("shop", shopService.getShopList(pageable));

        List<shopPage> pageNum = new ArrayList<shopPage>();
        for (int i = 0;
             i < (shopService.getShopPage() / 8) + 1 + (shopService.getShopPage() % 8 == 0 ? -1 : 0); i++) {
            shopPage sp = new shopPage(i, i + 1);
            pageNum.add(sp);
        }
        model.addAttribute("page", pageNum);


        return "/shop/main";
    }

    @GetMapping("/shop/upload")
    public String FileUploadGet() {

        return "/shop/upload";
    }

    @PostMapping("/shop/upload")
    public String FileUploadPost(@RequestParam("file") MultipartFile file,
                                 @RequestParam String shopTitle,
                                 @RequestParam String shopContent) throws IOException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        // 회원 정보 검색
        User user = userService.getUser(name);

        shopService.createShop(user, file, shopTitle, shopContent);

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/getShop/{shopNumber}")
    public String getShop(Model model, @PathVariable("shopNumber") Long shopNumber) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        Shop shop = shopService.getShop(shopNumber);

        model.addAttribute("name", name);
        model.addAttribute("shop", shop);

        return "/shop/getShop";
    }

    @GetMapping("/shop/modifyShop/{shopNumber}")
    public String modifyShop(@PathVariable("shopNumber") Long shopNumber, Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        Shop shop = shopService.getShop(shopNumber);

        model.addAttribute("name", name);
        model.addAttribute("shop", shop);

        return "/shop/modifyShop";
    }

    @PostMapping("/shop/modifyShop/{shopNumber}")
    public String modifyShop(@PathVariable("shopNumber") Long shopNumber,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam String shopTitle,
                             @RequestParam String shopContent) throws IOException {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        Shop shop = shopService.getShop(shopNumber);

        shopService.modifyShop(shop, shopTitle, shopContent, file);

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/deleteShop/{shopNumber}")
    public String deleteShop(@PathVariable("shopNumber") Long shopNumber, Model model) {


        model.addAttribute("shopNumber", shopNumber);

        return "/shop/deleteShopProc";
    }

    @GetMapping("/shop/deleteShopProc/{shopNumber}")
    public String deleteShopProc(@PathVariable("shopNumber") Long shopNumber) {

        shopService.deleteShop(shopNumber);

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/myShop")
    public String myShop(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        List<Shop> shop = shopService.getMyShop(name);

        model.addAttribute("shop", shop);

        return "/shop/myShop";
    }

}
