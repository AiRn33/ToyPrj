package com.toyprj.start.service;

import com.toyprj.start.entity.Board;
import com.toyprj.start.repository.BoardJpaRepostiory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepostiory boardJpaRepostiory;

    public List<Board> getBoardList(){

        List<Board> boards = boardJpaRepostiory.findAll();
        System.out.println("list 실행");
        System.out.println(boards.get(0));
        return boards;
    }

    public Board getBoard(Long boardnumber){

        Board board = boardJpaRepostiory.findById(boardnumber).orElse(null);

        return board;
    }

    public void createBoard(Board board){

        boardJpaRepostiory.save(board);
    }

    public void modifyBoard(Board board){

        boardJpaRepostiory.save(board);
    }

    public void deleteBoard(Long boardNumber){

        boardJpaRepostiory.deleteById(boardNumber);
    }


}
