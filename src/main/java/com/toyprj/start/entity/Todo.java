package com.toyprj.start.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "todo")
public class Todo {

    @Column(name = "id")
    Long id;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long todoNumber;

    @Column(name = "todo_title")
    String todoTitle;
    @Temporal(TemporalType.DATE)
    @Column(name = "todo_at")
    Date todoAt;

    @Column(name = "todo_success")
    Long todoSucess;

    @Builder
    public Todo(Long id, Long todoNumber, String todoTitle,Long todoSucess){
        this.id = id;
        this.todoNumber = todoNumber;
        this.todoTitle = todoTitle;
        this.todoAt = new Date();
        this.todoSucess = todoSucess;
    }

}
