package com.toyprj.start.Controller;

import com.toyprj.start.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/getBoardList")
    public String getBoardList(Model model){

        model.addAttribute("board", boardService.getBoardList());

        return "/getBoardList";
    }

    // 게시글 상세 조회
    public String getBoard(){

        return "";
    }

    // 게시글 생성
    public String createBoard(){

        return "";
    }

    // 게시글 수정
    public String modifyBoard(){

        return "";
    }

    // 게시글 삭제
    public String deleteBoard(){

        return "";
    }

}
