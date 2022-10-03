package com.toyprj.start.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board")
public class Board {

    @Column(name = "id")
    Long id;
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
    public Board(Long id, Long boardNumber, String boardWriter,
                    String boardTitle, String boardContent, int boardCount){
        this.id = id;
        this.boardNumber = boardNumber;
        this.boardWriter = boardWriter;
        this.boardTitle = boardTitle;
        this.boardContent = boardContent;
        this.boardAt = new Date();
        this.boardCount = boardCount;
    }

}
