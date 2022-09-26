package com.toyprj.start.repository;

import com.toyprj.start.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardJpaRepostiory extends JpaRepository<Board, Long> {
}
