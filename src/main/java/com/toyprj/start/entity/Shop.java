package com.toyprj.start.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "shop")
public class Shop {

    @Column(name = "id")
    Long id;
    @Id
    @Column(name = "shop_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long shopNumber;

    @Column(name = "shop_writer")
    String shopWriter;

    @Column(name = "shop_title")
    String shopTitle;

    @Column(name = "shop_content")
    String shopContent;

    @Column(name = "shop_at")
    Date shopAt;

    @Column(name = "shop_buycheck")
    int shopBuyCheck;

    @Column(name = "file_uuid")
    String fileUuid;

    @Column(name = "file_name")
    String fileName;

    @Builder
    public Shop(Long id, Long shopNumber, String shopWriter,
                String shopTitle, String shopContent,int shopBuyCheck,
                String fileUuid, String fileName){

        this.id = id;
        this.shopNumber = shopNumber;
        this.shopWriter = shopWriter;
        this.shopTitle = shopTitle;
        this.shopContent = shopContent;
        this.shopAt = new Date();
        this.shopBuyCheck = shopBuyCheck;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
    }

}
