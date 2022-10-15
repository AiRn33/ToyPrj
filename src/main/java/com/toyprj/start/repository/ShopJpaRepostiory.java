package com.toyprj.start.repository;

import com.toyprj.start.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface ShopJpaRepostiory extends JpaRepository<Shop, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into shop(id,shop_writer,shop_title,shop_content,shop_at,shop_buycheck,file_data,file_name) " +
            "values(:id, :shop_writer, :shop_title, :shop_content, :shop_at, :shop_buycheck, :file_data, :file_name)", nativeQuery = true)
    void createShop(@Param("id") Long id, @Param("shop_writer") String shopWriter,
                   @Param("shop_title") String shopTitle, @Param("shop_content") String shopContent,
                   @Param("shop_at")Date shopAt, @Param("shop_buycheck") int shopBuyCheck,
                    @Param("file_data") byte[] fileData, @Param("file_name")String fileName);


    @Query(value = "select * from shop where shop_number = :shop_number")
    Shop getShop(@Param("shop_number") Long shopNumber);
}
