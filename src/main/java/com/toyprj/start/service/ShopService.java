package com.toyprj.start.service;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.Shop;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardDto;
import com.toyprj.start.repository.BoardJpaRepostiory;
import com.toyprj.start.repository.ShopJpaRepostiory;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ShopService {

    private final ShopJpaRepostiory shopJpaRepostiory;
    private final UserJpaRepository userJpaRepository;

    public void createShop(User user, MultipartFile file, String shopTitle, String shopContent) throws IOException {

        shopJpaRepostiory.createShop(user.getId(), user.getUserName(), shopTitle, shopContent,
                new Date(), 0, file.getBytes(), file.getName());

    }

    public Shop getShop(Long shopNumber){

        Shop shop = shopJpaRepostiory.getShop(shopNumber);

       return shop;
    }
}


