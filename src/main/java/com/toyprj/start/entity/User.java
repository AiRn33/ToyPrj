package com.toyprj.start.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long number;

    private String userId;

    private String userPassword;

    private String userName;

    private Date userAt;

    @Builder
    public User(Long number, String userId, String userPassword
            , String userName){
        this.number = number;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAt = new Date();
    }
}
