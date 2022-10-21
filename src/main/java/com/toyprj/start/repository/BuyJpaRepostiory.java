package com.toyprj.start.repository;

import com.toyprj.start.entity.Buy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyJpaRepostiory extends JpaRepository<Buy, Long> {
}
