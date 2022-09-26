package com.toyprj.start.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "board")
public class Board {

    @Column(name = "number")
    Long number;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long boardNumber;

    @Column(name = "board_writer")
    String boardWriter;

    @Column(name = "board_title")
    String boardTitle;

    @Column(name = "board_content")
    String boardContent;

    @Column(name = "board_at")
    Date boardAt;

    @Column(name = "board_count")
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
