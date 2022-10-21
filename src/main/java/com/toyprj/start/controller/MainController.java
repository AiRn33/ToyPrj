package com.toyprj.start.controller;

import com.toyprj.start.service.BoardService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.StandardSession;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final BoardService boardService;
    // 메인 페이지
    @GetMapping("/main")
    public String index(Model model, HttpSession session){

        String name = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            name = userDetails.getUsername();
        }catch (Exception e){
            name = null;
        }
        if(name != null){
            model.addAttribute("name", name);
        }
        return "/main";
    }

    //로그인
    @GetMapping("/login")
    public String login() {

        return "/login";
    }

    @GetMapping("/logoutForm")
    public String logout() {
        return "/logoutForm";
    }

    @GetMapping("/findPassword")
    public String findPassword(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        return "/findPasswordForm";
    }

    @PostMapping("/findPasswordForm")
    public String findPasswordForm(@RequestParam("user_name") String userName, Model model){


       String userId = userService.findUser(userName);

       model.addAttribute("userId", userId);

        return "/findPasswordProc";
    }

    // 회원가입
    @GetMapping("/signupCheck")
    public String signUpCheck() {

        return "/signupCheck";
    }
    @GetMapping("/signup")
    public String signUp() {

        return "/signup";
    }
    @GetMapping("/signupManager")
    public String signUpManager() {

        return "/signupManager";
    }

    @PostMapping("/signupProc")
    public String signUpProc(@RequestParam("userId") String userId,
                             @RequestParam("userPassword") String userPassword,
                             @RequestParam("userName") String userName,
                             @RequestParam("check") String check) {

        userService.signup(userId, userPassword, userName, check);

        return "redirect:/main";
    }

    @GetMapping("/exception")
    public String a() {

        return "/exception";
    }
}
