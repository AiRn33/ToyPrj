package com.toyprj.start.entity;

import com.toyprj.start.model.BuyDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "buy")
public class Buy {


    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "sell_id")
    Long sellId;

    @Column(name = "buy_id")
    Long buyId;

    @Column(name = "buy_amount")
    Long buyAmount;

    @Column(name = "shop_number")
    Long shopNumber;

    @Column(name = "shop_price")
    Long shopPrice;


    public Buy(BuyDto dto) {
        this.sellId = dto.getSellId();
        this.buyId = dto.getBuyId();
        this.buyAmount = dto.getBuyAmount();
        this.shopNumber = dto.getShopNumber();
        this.shopPrice = dto.getShopPrice();
    }
}
