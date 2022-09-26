package com.toyprj.start.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    // 메인 페이지
    @GetMapping("/index")
    public String index(){

        return "/index";
    }

    // 로그인 컨트롤러
    public String login(){

        return "";
    }

    // 로그아웃 컨트롤러
    public String logout(){

        return "";
    }

    
}
