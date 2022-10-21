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

    @Column(name = "shop_amount")
    Long shopAmount;

    @Column(name = "shop_price")
    Long shopPrice;

    @Column(name = "file_uuid")
    String fileUuid;

    @Column(name = "file_name")
    String fileName;

    @Builder
    public Shop(Long id, Long shopNumber, String shopWriter,
                String shopTitle, String shopContent,Long shopAmount,
                Long shopPrice,String fileUuid, String fileName){

        this.id = id;
        this.shopNumber = shopNumber;
        this.shopWriter = shopWriter;
        this.shopTitle = shopTitle;
        this.shopContent = shopContent;
        this.shopAt = new Date();
        this.shopAmount = shopAmount;
        this.shopPrice = shopPrice;
        this.fileUuid = fileUuid;
        this.fileName = fileName;
    }

}
