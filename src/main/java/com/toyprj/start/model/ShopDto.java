package com.toyprj.start.model;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.Shop;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class ShopDto {

    Long id;

    Long shopNumber;

    String shopWriter;

    String shopTitle;

    String shopContent;

    Date shopAt;

    int shopBuyCheck;

    String fileUuid;

    String fileName;


    @Builder
    public Shop toEntity(){

        return Shop.builder()
                .id(id)
                .shopNumber(shopNumber)
                .shopWriter(shopWriter)
                .shopTitle(shopTitle)
                .shopContent(shopContent)
                .shopAt(new Date())
                .shopBuyCheck(shopBuyCheck)
                .fileUuid(fileUuid)
                .fileName(fileName)
                .build();
    }

}
