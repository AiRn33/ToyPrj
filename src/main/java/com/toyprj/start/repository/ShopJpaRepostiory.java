package com.toyprj.start.repository;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ShopJpaRepostiory extends JpaRepository<Shop, Long> {
    @Modifying
    @Transactional
    @Query(value = "insert into shop(id,shop_writer,shop_title,shop_content,shop_at,file_uuid,file_name,shop_amount,shop_price) " +
            "values(:id, :shop_writer, :shop_title, :shop_content, :shop_at, :file_uuid, :file_name, :shop_amount, :shop_price)", nativeQuery = true)
    void createShop(@Param("id") Long id, @Param("shop_writer") String shopWriter,
                   @Param("shop_title") String shopTitle, @Param("shop_content") String shopContent,
                   @Param("shop_at")Date shopAt,
                    @Param("file_uuid") String fileUuid, @Param("file_name")String fileName,
                    @Param("shop_amount")String shop_amount, @Param("shop_price")String shopPrice);


    @Query(value = "select * from shop where shop_number = :shop_number", nativeQuery = true)
    Shop getShop(@Param("shop_number")Long shopNumber);

    @Query(value = "select * from shop where id = :id", nativeQuery = true)
    List<Shop> getSellShopList(@Param("id")Long id);

    @Query(value = "select count(shop_number) from shop", nativeQuery = true )
    int pageNumberCheckMember();

    @Query(value = "update shop set shop_amount = " +
            ":shop_amount where shop_number = :shop_number", nativeQuery = true)
    @Modifying
    @Transactional
    void discountAmount(@Param("shop_amount") Long shop_amount, @Param("shop_number") Long shop_number);

    @Query(value = "select * from shop where shop_title like %:search% or shop_content like %:search%", nativeQuery = true )
    List<Shop> searchShop(@Param("search")String search);
}
