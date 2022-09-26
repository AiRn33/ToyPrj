package com.toyprj.start.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    Long number;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long boardNumber;

    String boardWriter;

    String boardTitle;

    String boardContent;

    Date boardAt;

    int boardCount;

    @Builder
    public Board(Long number, Long boardNumber, String boardWriter,
                 String boardTitle, String boardContent, int boardCount){
        this.number = number;
        this.boardNumber = boardNumber;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAt = new Date();
        this.boardCount = boardCount;
    }


}
