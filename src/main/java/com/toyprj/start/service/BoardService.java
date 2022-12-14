package com.toyprj.start.service;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardDto;
import com.toyprj.start.repository.BoardJpaRepostiory;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardJpaRepostiory boardJpaRepostiory;
    private final UserJpaRepository userJpaRepository;

    // 페이징 처리
    @Transactional
    public Page<Board> getBoardList(Pageable pageable) {

        return boardJpaRepostiory.findAll(pageable);
    }

    public int getBoardPage(){
        return boardJpaRepostiory.pageNumberCheck();
    }

    public Board getBoard(Long boardnumber){

        return boardJpaRepostiory.findById(boardnumber).orElse(null);
    }

    public void createBoard(String boardTitle,
                            String boardContent, String name){

        User user = userJpaRepository.findByuserId(name);

        BoardDto dto = new BoardDto(user.getId(),
                user.getUserName(), boardTitle, boardContent);

        boardJpaRepostiory.save(dto.toEntity());
    }

    public void modifyBoard(Long boardNumber,String boardTitle, String boardContent){

        Board board = boardJpaRepostiory.findById(boardNumber).orElse(null);

        BoardDto dto = new BoardDto();

        dto.setId(board.getId());
        dto.setBoardNumber(boardNumber);
        dto.setBoardWriter(board.getBoardWriter());
        dto.setBoardTitle(boardTitle);
        dto.setBoardContent(boardContent);

        boardJpaRepostiory.save(dto.toEntity());
    }

    public void deleteBoard(Long boardNumber){

        boardJpaRepostiory.deleteById(boardNumber);
    }


}
