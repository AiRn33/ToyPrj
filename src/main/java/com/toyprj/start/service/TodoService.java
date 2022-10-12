package com.toyprj.start.service;

import com.toyprj.start.entity.Board;
import com.toyprj.start.entity.Todo;
import com.toyprj.start.entity.User;
import com.toyprj.start.model.BoardDto;
import com.toyprj.start.model.TodoDto;
import com.toyprj.start.repository.BoardJpaRepostiory;
import com.toyprj.start.repository.TodoJpaRepostiory;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoJpaRepostiory todoJpaRepostiory;

    public void createList(String todoTitle, Long id) {

        TodoDto dto = new TodoDto();

        dto.setId(id);
        dto.setTodoTitle(todoTitle);

        todoJpaRepostiory.save(dto.toEntity());
    }


    public List<Todo> getTodoList(Long id) {

        List<Todo> list = todoJpaRepostiory.findByUserId(id);

        return list;
    }
    public List<Todo> getTodoComplete(Long id) {

        List<Todo> list = todoJpaRepostiory.findByUserIdComplete(id);

        return list;
    }

    public int getTodoListCount(Long id){

        int count = todoJpaRepostiory.getCountTodoList(id);

        return count;
    }

    public void TodoListComplete(Long todoNumber){

        todoJpaRepostiory.getTodoComplete(todoNumber);
    }

    public void getTodoListDelete(Long todoNumber) {

        todoJpaRepostiory.getTodoDelete(todoNumber);
    }
}
