package com.toyprj.start.Controller;

import com.toyprj.start.entity.Board;
import com.toyprj.start.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.StyleSheet;

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
    @GetMapping("/getBoard/{boardNumber}")
    public String getBoard(@PathVariable("boardNumber") Long boardNumber,Model model){

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "/getBoard";
    }

    // 게시글 생성
    public String createBoard(){

        return "";
    }

    // 게시글 수정
    @GetMapping("/modifyBoard/{boardNumber}")
    public String modifyGetBoard(@PathVariable("boardNumber") Long boardNumber,
                              Model model){

        model.addAttribute("board",boardService.getBoard(boardNumber));

        return "/modifyBoard";
    }

    @PostMapping("/modifyBoard/{boardNumber}")
    public String modifyPostBoard(@PathVariable("boardNumber") Long boardNumber,
                              Model model, @RequestParam("boardTitle") String boardTitle,
                                  @RequestParam("boardContent") String boardContent){

        return "redirect:/getBoard/"+boardNumber;
    }
    // 게시글 삭제
    public String deleteBoard(){

        return "";
    }

}
