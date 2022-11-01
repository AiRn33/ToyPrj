package com.toyprj.start.controller;

import com.toyprj.start.model.BoardPage;
import com.toyprj.start.recode.SetModelName;
import com.toyprj.start.service.BoardService;
import com.toyprj.start.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final UserService userService;

    // 전체 게시판 조회
    @GetMapping("/board/getBoardList")
    public String getBoardListPage(Model model, @PageableDefault(size = 7, sort = "id",
                                                direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("board", boardService.getBoardList(pageable));

        List<BoardPage> pageNum = new ArrayList<BoardPage>();


        int check = ((pageable.getPageNumber() < 3) ?
                pageable.getPageNumber() + (5 - pageable.getPageNumber())
                : pageable.getPageNumber() + 3);

        for (int i = (pageable.getPageNumber() - 2) < 0 ? 0 :
                                pageable.getPageNumber() - 2;
             i < ((check <= boardService.getBoardPage() / 7) ? check :
                     (boardService.getBoardPage() % 7 != 0 ?
                             (boardService.getBoardPage() / 7) + 1 :
                             boardService.getBoardPage() / 7)); i++) {

            BoardPage bp = new BoardPage(i, i + 1);
            pageNum.add(bp);

        }
        int startPage = pageable.getPageNumber() - 3;
        if (pageable.getPageNumber() != 0) {
            model.addAttribute("start", startPage <= 0 ? 0 : startPage);
        }

        int endPage = boardService.getBoardPage() % 7 != 0 ? (boardService.getBoardPage() / 7) + 1 : boardService.getBoardPage() / 7;
        if(pageable.getPageNumber() + 3 < endPage){
            model.addAttribute("end", pageable.getPageNumber() + 3);
        }
        // 페이지 넘버는 16


        model.addAttribute("page", pageNum);


        SetModelName setModelName = new SetModelName(model);

        return "/board/getBoardList";
    }


    // 게시글 상세 조회
    @GetMapping("/board/getBoard/{boardNumber}")
    public String getBoard(@PathVariable("boardNumber") Long boardNumber, Model model) {

        SetModelName setModelName = new SetModelName(model);

        if (boardService.getBoard(boardNumber).getId() == userService.getUser(setModelName.getName()).getId()) {
            model.addAttribute("check", 1);
        }

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "/board/getBoard";
    }

    // 게시글 생성
    @GetMapping("/board/createBoard")
    public String createBoard(Model model) {

        SetModelName setModelName = new SetModelName(model);

        return "/board/createBoard";
    }

    @PostMapping("/createBoardProc")
    public String createBoardProc(@RequestParam String boardTitle,
                                  @RequestParam String boardContent) {

        SetModelName setModelName = new SetModelName();


        boardService.createBoard(boardTitle, boardContent, setModelName.getName());

        return "redirect:/board/getBoardList";
    }


    // 게시글 수정
    @GetMapping("/board/modifyBoard/{boardNumber}")
    public String modifyGetBoard(@PathVariable("boardNumber") Long boardNumber,
                                 Model model) {

        SetModelName setModelName = new SetModelName(model);

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "/board/modifyBoard";
    }

    @PostMapping("/modifyBoard/{boardNumber}")
    public String modifyPostBoard(@PathVariable("boardNumber") Long boardNumber,
                                  Model model, @RequestParam("boardTitle") String boardTitle,
                                  @RequestParam("boardContent") String boardContent) {

        boardService.modifyBoard(boardNumber, boardTitle, boardContent);

        model.addAttribute("board", boardService.getBoard(boardNumber));

        return "redirect:/board/getBoard/" + boardNumber;
    }

    // 게시글 삭제
    @GetMapping("/board/deleteBoard/{boardNumber}")
    public String deleteBoard(@PathVariable("boardNumber") Long boardNumber
            , Model model) {

        model.addAttribute("boardNumber", boardNumber);

        return "/board/deleteBoardProc";
    }

    @GetMapping("/deleteBoardProc/{boardNumber}")
    public String deleteProc(@PathVariable("boardNumber") Long boardNumber) {

        boardService.deleteBoard(boardNumber);

        return "redirect:/board/getBoardList";
    }
}
