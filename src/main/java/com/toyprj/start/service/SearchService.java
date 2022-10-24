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
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final BoardJpaRepostiory boardJpaRepostiory;
    private final UserJpaRepository userJpaRepository;
    private final ShopJpaRepostiory shopJpaRepostiory;

    public List<Board> searchBoard(String search){

        return boardJpaRepostiory.searchBoard(search);
    }

    public List<Shop> searchShop(String search){

        return shopJpaRepostiory.searchShop(search);
    }

}
