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
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequiredArgsConstructor
public class ShopController {

    private final ShopService shopService;
    private final UserService userService;


    @GetMapping("/shop/main")
    public String getBoardListPage(Model model, @PageableDefault(size = 8, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        model.addAttribute("shop", shopService.getShopList(pageable));

        List<shopPage> pageNum = new ArrayList<shopPage>();
        for(int i = 0;
            i < (shopService.getShopPage() / 8) + 1 + (shopService.getShopPage() % 8 == 0 ? -1 : 0); i++) {
            shopPage sp = new shopPage(i, i + 1);
            pageNum.add(sp);
        }
        model.addAttribute("page", pageNum);


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
        String uuid = UUID.randomUUID().toString();

        int count = 0;
        for(int i = 0; i < file.getOriginalFilename().length(); i++){

            if(file.getOriginalFilename().charAt(i) == '.'){
                count = i;
            }
        }

        String path = "C:\\start\\src\\main\\resources\\static\\img\\" + user.getId(); //폴더 경로
        File Folder = new File(path);

        // 해당 디렉토리가 없을경우 디렉토리를 생성합니다.
        if (!Folder.exists()) {
            try{
                Folder.mkdir(); //폴더 생성합니다.
            }
            catch(Exception e){
                e.getStackTrace();
            }
        }
        file.transferTo(new File(path + "\\" + uuid + "."
                + file.getOriginalFilename().substring(count+1, file.getOriginalFilename().length())));

        shopService.createShop(user,file,uuid + "."
                + file.getOriginalFilename().substring(count+1, file.getOriginalFilename().length()),shopTitle,shopContent);

        return "redirect:/shop/main";
    }

    @GetMapping("/shop/getShop/{shopNumber}")
    public String getShop(Model model,@PathVariable("shopNumber")Long shopNumber){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        Shop shop = shopService.getShop(shopNumber);
        System.out.println(shop.getShopTitle());

        model.addAttribute("name",name);
        model.addAttribute("shop",shop);

        return "/shop/getShop";
    }

    @GetMapping("/shop/modifyShop")
    public String modifyShop(){

        return "/shop/modifyShop";
    }
}
