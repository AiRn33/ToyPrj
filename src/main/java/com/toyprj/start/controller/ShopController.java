package com.toyprj.start.controller;

import com.toyprj.start.model.BoardPage;
import com.toyprj.start.service.BoardService;
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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ShopController {

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
    public String FileUploadPost(@RequestParam("file") MultipartFile file) throws IOException {


        System.out.println(file.getContentType());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getBytes());
        System.out.println(file.getName());

        return "/main";
    }
}
