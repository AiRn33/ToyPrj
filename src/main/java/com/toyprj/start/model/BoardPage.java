package com.toyprj.start.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BoardPage {

    private int pageNum;
    private int pageView;
    public BoardPage(int pageNum, int pageView) {
        this.pageNum = pageNum;
        this.pageView = pageView;
    }
}
