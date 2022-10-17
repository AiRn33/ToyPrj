package com.toyprj.start.controller;

import com.toyprj.start.entity.Shop;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardPage;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;


    @GetMapping("/shop/main")
    public String shopMain(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        return "/shop/main";
    }

    @GetMapping("/shop/upload")
    public String FileUploadGet(){

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

        shopService.createShop(user,file,shopTitle,shopContent);

        return "/main";
    }

    @GetMapping("/shop/getShop")
    public String getShop(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        System.out.println(userService.getUser(name).getId());

        Shop shop = shopService.getShop(1L);
        InputStream in1 = new ByteArrayInputStream(shop.getFileData());

        model.addAttribute("shop",shop);
        model.addAttribute("img", in1);

        return "/shop/getShop";
    }
}
