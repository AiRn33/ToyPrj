package com.toyprj.start.repository;

import com.toyprj.start.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyJpaRepostiory extends JpaRepository<Buy, Long> {

    @Query(value = "select * from buy where sell_id = :sell_id", nativeQuery = true )
    List<Buy> selllCheck(@Param("sell_id")Long sellId);

    @Query(value = "select * from buy where buy_id = :buy_id", nativeQuery = true )
    List<Buy> buyCheck(@Param("buy_id")Long buyId);
}
