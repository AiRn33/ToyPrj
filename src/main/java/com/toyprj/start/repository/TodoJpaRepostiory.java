package com.toyprj.start.repository;

import com.toyprj.start.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface TodoJpaRepostiory extends JpaRepository<Todo, Long> {

    @Query(value = "select * from todo where id = :id and todo_success = 0", nativeQuery = true)
    List<Todo> findByUserId(@Param("id") Long id);

    @Query(value = "select * from todo where id = :id and todo_success = 1 ORDER BY todo_number desc limit 10;", nativeQuery = true)
    List<Todo> findByUserIdComplete(@Param("id") Long id);

    @Query(value = "select count(*) from todo where id = :id and todo_success = 0", nativeQuery = true)
    int getCountTodoList(@Param("id") Long id);

    @Query(value = "update todo set todo_success = 1 where todo_number = :todo_number", nativeQuery = true)
    @Modifying
    @Transactional
    void getTodoComplete(@Param("todo_number") Long todoNumber);

    @Query(value = "delete from todo where todo_number=:todo_number", nativeQuery = true)
    @Modifying
    @Transactional
    void getTodoDelete(@Param("todo_number") Long todoNumber);
}
