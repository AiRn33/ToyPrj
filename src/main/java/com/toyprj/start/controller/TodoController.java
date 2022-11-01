package com.toyprj.start.controller;

import com.toyprj.start.entity.Todo;
import com.toyprj.start.recode.SetModelName;
import com.toyprj.start.service.TodoService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TodoController {


    private final TodoService todoService;
    private final UserService userService;

    @GetMapping("/todo/list")
    public String todo(Model model) {

        SetModelName setModelName = new SetModelName(model);

        try {

            Long id = userService.getUser(setModelName.getName()).getId();

            List list = todoService.getTodoList(id);

            model.addAttribute("list", list);
        } catch (Exception e) {

            model.addAttribute("list", null);
        }

        return "/todo/list";
    }

    @GetMapping("/todo/listSuccess")
    public String todoSuccess(Model model) {

        SetModelName setModelName = new SetModelName(model);

        try {

            Long id = userService.getUser(setModelName.getName()).getId();

            List list = todoService.getTodoComplete(id);

            model.addAttribute("list", list);
        } catch (Exception e) {
            model.addAttribute("list", null);
        }

        return "/todo/listSuccess";
    }

    @PostMapping("/todo/listProc")
    @ResponseBody
    public int todoCreate(@RequestParam("todo_title") String todoTitle) {

        SetModelName setModelName = new SetModelName();

        Long id = userService.getUser(setModelName.getName()).getId();

        int count = todoService.getTodoListCount(id);

        if (count < 5) {
            todoService.createList(todoTitle, id);
            return 1;
        }
        return 0;
    }

    @GetMapping("/todo/complete")
    public String todoComplete(@RequestParam("todo_number") Long todoNumber,Model model) {


        SetModelName setModelName = new SetModelName(model);

        todoService.TodoListComplete(todoNumber);

        return "redirect:/todo/list";
    }

    @GetMapping("/todo/deleteList")
    public String todoDeleteList(@RequestParam("todo_number") Long todoNumber,Model model) {


        SetModelName setModelName = new SetModelName(model);

        todoService.getTodoListDelete(todoNumber);

        return "redirect:/todo/list";
    }


}
