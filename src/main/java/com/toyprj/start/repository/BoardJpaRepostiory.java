package com.toyprj.start.repository;

import com.toyprj.start.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardJpaRepostiory extends JpaRepository<Board, Long> {

    @Query(value = "select count(board_number) from board;", nativeQuery = true )
    int pageNumberCheck();

}
