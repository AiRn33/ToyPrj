package com.toyprj.start.Controller;

import com.toyprj.start.entity.Board;
import com.toyprj.start.model.BoardDto;
import com.toyprj.start.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.StyleSheet;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    // 게시글 전체 조회
    @GetMapping("/board/getBoardList")
    public String getBoardList(Model model){

        model.addAttribute("board", boardService.getBoardList(0));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        return "/board/getBoardList";
    }
    @GetMapping("/board/getBoardList/{page}")
    public String getBoardListPage(Model model, @PathVariable("page") int page){

        model.addAttribute("board", boardService.getBoardList(0));

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        return "/board/getBoardList";
    }



    // 게시글 상세 조회
    @GetMapping("/board/getBoard/{boardNumber}")
    public String getBoard(@PathVariable("boardNumber") Long boardNumber,Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "/board/getBoard";
    }

    // 게시글 생성
    @GetMapping("/board/createBoard")
    public String createBoard(){

        return "/board/createBoard";
    }

    @PostMapping("/createBoardProc")
    public String createBoardProc(@RequestParam String boardTitle,
                                  @RequestParam String boardContent){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        boardService.createBoard(boardTitle, boardContent, name);

        return "redirect:/board/getBoardList";
    }


    // 게시글 수정
    @GetMapping("/board/modifyBoard/{boardNumber}")
    public String modifyGetBoard(@PathVariable("boardNumber") Long boardNumber,
                              Model model){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String name = userDetails.getUsername();

        model.addAttribute("name", name);

        model.addAttribute("board",boardService.getBoard(boardNumber));

        return "/board/modifyBoard";
    }

    @PostMapping("/modifyBoard/{boardNumber}")
    public String modifyPostBoard(@PathVariable("boardNumber") Long boardNumber,
                              Model model, @RequestParam("boardTitle") String boardTitle,
                                  @RequestParam("boardContent") String boardContent){

        boardService.modifyBoard(boardNumber ,boardTitle,boardContent);

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "redirect:/board/getBoard/"+boardNumber;
    }
    // 게시글 삭제
    @GetMapping("/board/deleteBoard/{boardNumber}")
    public String deleteBoard(@PathVariable("boardNumber") Long boardNumber
                            ,Model model){

        model.addAttribute("boardNumber",boardNumber);

        return "/board/deleteBoardProc";
    }

    @GetMapping("/deleteBoardProc/{boardNumber}")
    public String deleteProc(@PathVariable("boardNumber") Long boardNumber){

        System.out.println("boardNumber : " + boardNumber);
        boardService.deleteBoard(boardNumber);

        return "redirect:/board/getBoardList";
    }
}
