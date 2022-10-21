package com.toyprj.start.model;

import com.toyprj.start.entity.Buy;
import lombok.Builder;
import lombok.Data;

@Data
public class BuyDto {

    Long sellId;

    Long buyId;

    Long buyAmount;

    Long shopNumber;

    Long shopPrice;

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
