package com.toyprj.start.model;

import com.toyprj.start.entity.Buy;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BuyDto {

    Long sellId;

    Long buyId;

    Long buyAmount;

    Long shopNumber;

    Long shopPrice;

    String shopTitle;

    String buyName;

    String sellName;

    // 판매자용 DTO
    public BuyDto(Long shopNumber,String shopTitle, String buyName, Long buyAmount){

        this.shopNumber = shopNumber;
        this.shopTitle = shopTitle;
        this.buyName = buyName;
        this.buyAmount = buyAmount;

    }

    // 구매자용 DTO
    public BuyDto(Long buyAmount, Long shopNumber, String shopTitle, String sellName) {
        this.buyAmount = buyAmount;
        this.shopNumber = shopNumber;
        this.shopTitle = shopTitle;
        this.sellName = sellName;
    }

    @Builder
    public Buy toEntity(){

       return Buy.builder()
               .sellId(sellId)
               .buyId(buyId)
               .buyAmount(buyAmount)
               .shopNumber(shopNumber)
               .shopPrice(shopPrice)
               .build();
   }


}
