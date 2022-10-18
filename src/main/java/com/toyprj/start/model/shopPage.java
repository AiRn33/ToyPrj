package com.toyprj.start.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class shopPage {

    private int pageNum;
    private int pageView;
    public shopPage(int pageNum, int pageView) {
        this.pageNum = pageNum;
        this.pageView = pageView;
    }
}
