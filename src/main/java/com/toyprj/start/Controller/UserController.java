package com.toyprj.start.Controller;

import com.toyprj.start.entity.User;
import com.toyprj.start.model.UserDto;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    // 회원가입
    @GetMapping("/user/signup")
    public String signUp() {

        return "/user/signup";
    }

    @PostMapping("/signupProc")
    public String signUpProc(@RequestParam("userId") String userId,
                             @RequestParam("userPassword") String userPassword,
                             @RequestParam("userName") String userName) {

        userService.signup(userId, userPassword, userName);
        return "redirect:/main";
    }
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
}
