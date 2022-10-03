package com.toyprj.start.model;

import com.toyprj.start.entity.Board;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
public class BoardDto {

    Long id;

    Long boardNumber;

    String boardWriter;

    String boardTitle;

    String boardContent;

    Date boardAt;

    int boardCount;

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
