package com.toyprj.start.controller;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.Shop;
import com.toyprj.start.recode.SetModelName;
import com.toyprj.start.service.BoardService;
import com.toyprj.start.service.SearchService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
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

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @PostMapping("/search")
    public String search(@RequestParam("val") String val
                        ,@RequestParam("search") String search
                        ,Model model){

        SetModelName setModelName = new SetModelName(model);

        if(val.equals("Board")){

            // 게시판 검색
            List<Board> result = searchService.searchBoard(search);

            model.addAttribute("board", result);

            return "/board/getBoardList";

        }else if(val.equals("Shop")){

            // 쇼핑몰 검색
            List<Shop> result = searchService.searchShop(search);

            model.addAttribute("shop", result);

            return "/shop/main";
        }

        return "/main";
    }
}
