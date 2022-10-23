package com.toyprj.start.service;

import com.toyprj.start.entity.Buy;
import com.toyprj.start.entity.Shop;
import com.toyprj.start.model.BuyDto;
import com.toyprj.start.repository.BuyJpaRepostiory;
import com.toyprj.start.repository.ShopJpaRepostiory;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BuyService {

    private final ShopJpaRepostiory shopJpaRepostiory;
    private final UserJpaRepository userJpaRepository;
    private final BuyJpaRepostiory buyJpaRepostiory;


    // 물품 구매
    public int buyShop(Shop shop, Long id, Long shopAmount){

        if(shop.getShopAmount() - shopAmount < 0){
            return -1;
        }

        shopJpaRepostiory.discountAmount(shop.getShopAmount() - shopAmount, shop.getShopNumber());

        BuyDto dto = new BuyDto();

        dto.setBuyId(id);
        dto.setSellId(shop.getId());
        dto.setBuyAmount(shopAmount);
        dto.setShopNumber(shop.getShopNumber());
        dto.setShopPrice(shop.getShopPrice());

        Buy buy = new Buy(dto);

        buyJpaRepostiory.save(buy);

        return 1;
    }
}


