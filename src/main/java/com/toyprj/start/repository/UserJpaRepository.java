package com.toyprj.start.repository;

import com.toyprj.start.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>{

    @Query(value = "select * from user where user_id = :user_id", nativeQuery = true )
    User findByuserId(@Param("user_id")String userId);
}
