package com.toyprj.start.controller;

import com.toyprj.start.entity.User;
import com.toyprj.start.model.UserDto;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;



    // 회원 탈퇴

    @GetMapping("/user/profileDelete")
    public String profileDelete() {

        return "/user/profileDelete";
    }

    @GetMapping("/profileDeleteProc")
    public String profileDeleteProc() {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String id = userDetails.getUsername();

        userService.deleteUser(id);
        return "redirect:/logout";
    }

    // 회원 수정

    @GetMapping("/user/profileUpdate")
    public String profileUpdate(Model model) {

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        User user = userService.getUser(name);
        model.addAttribute("name", user);

        return "/user/profileUpdate";
    }

    @PostMapping("/profileUpdateProc")
    public String profileUpdateProc(UserDto dto, Model model) {

        userService.updateUser(dto.getUserName());

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        User user = userService.getUser(name);
        model.addAttribute("name", user);
        return "redirect:/user/mypage";
    }

    // 마이페이지 컨트롤러
    @GetMapping("/user/mypage")
    public String myPage(Model model) {


        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        User user = userService.getUser(name);

        model.addAttribute("name", user);

        return "/user/mypage";
    }

    // 비밀번호 관련

    @GetMapping("/user/pwModify")
    public String pwModify(Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        return "/user/pwModifyForm";
    }

    @PostMapping("/user/pwModifyProc")
    public String pwModifyProc(@RequestParam("userPassword") String pw, Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        userService.modifyPw(pw,name);

        User user = userService.getUser(name);

        model.addAttribute("name", user);

        return "/user/mypage";
    }

    @GetMapping("/session")
    public String session(@AuthenticationPrincipal User user, HttpSession session){

        System.out.println(user.getUserId());
        System.out.println(user.getUserName());
        return "main";
    }
}
