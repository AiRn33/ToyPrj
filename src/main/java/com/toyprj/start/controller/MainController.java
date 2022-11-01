package com.toyprj.start.controller;

import com.toyprj.start.service.BoardService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.session.StandardSession;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final UserService userService;
    private final BoardService boardService;

    // 메인 페이지
    @GetMapping("/main")
    public String index(Model model, HttpSession session) {

        String name = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            UserDetails userDetails = (UserDetails) principal;
            name = userDetails.getUsername();
        } catch (Exception e) {
            name = null;
        }
        if (name != null) {
            model.addAttribute("name", name);
        }
        return "/main";
    }

    //로그인
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "exception", required = false) String exception, Model model) {

        model.addAttribute("error", error);
        model.addAttribute("exception", exception);

        return "/login";
    }

    @GetMapping("/logoutForm")
    public String logout() {
        return "/logoutForm";
    }

    @GetMapping("/findId")
    public String findPassword(Model model) {

        return "/findIdForm";
    }

    @PostMapping("/findIdForm")
    public String findPasswordForm(@RequestParam("user_name") String userName, Model model) {


        String userId = userService.findUser(userName);

        model.addAttribute("userId", userId);

        return "/findIdProc";
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

    @ResponseBody
    @PostMapping("/signupIdCheck")
    public int signUpIdCheck(@RequestParam("userId")String id,
                             @RequestParam("userPassword")String password,
                             @RequestParam("userName")String name){

        if(userService.getUser(id)==null){
            return 1;
        }else{
            return 0;
        }
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

        System.out.println("Proc STart!!");
        userService.signup(userId, userPassword, userName, check);

        return "redirect:/main";
    }


}
