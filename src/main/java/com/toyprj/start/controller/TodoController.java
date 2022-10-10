package com.toyprj.start.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class TodoController {

    @GetMapping("/todo")
    public String todo(){

        return "/todo";
    }

}
