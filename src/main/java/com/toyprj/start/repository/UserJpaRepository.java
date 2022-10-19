package com.toyprj.start.repository;

import com.toyprj.start.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{

    @Query(value = "select * from user where user_id = :user_id", nativeQuery = true )
    User findByuserId(@Param("user_id")String userId);

    @Query(value = "update user set user_password = :user_password where user_id = :user_id", nativeQuery = true)
    @Modifying
    @Transactional
    void modifyByuserPassword(@Param("user_password")String userPassword, @Param("user_id") String id);

    @Query(value = "select user_id from user where user_name = :user_name", nativeQuery = true )
    String findByuserName(@Param("user_name")String userName);

    @Query(value = "select user_myshop from user where id = :id", nativeQuery = true )
    String findMyShop(@Param("id")Long id);


}
