package com.toyprj.start.model;

import com.toyprj.start.entity.Board;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BoardDto {

    Long id;

    Long boardNumber;

    String boardWriter;

    String boardTitle;

    String boardContent;

    Date boardAt;

    int boardCount;


    public BoardDto(Long id, String boardWriter, String boardTitle, String boardContent) {
        this.id = id;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAt = new Date();
        this.boardCount = 0;
    }

    @Builder
    public Board toEntity(){

        return Board.builder()
                .id(id)
                .boardNumber(boardNumber)
                .boardWriter(boardWriter)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .boardCount(boardCount)
                .boardAt(new Date())
                .build();
    }

}
