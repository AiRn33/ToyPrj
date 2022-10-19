package com.toyprj.start.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Builder
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_password")
    private String userPassword;

    @Column(name = "user_name")
    private String userName;

    private Date userAt;

    @Column(name = "roles", nullable = false)
    private String roles;

    @Column(name = "user_myshop", nullable = false)
    private String userMyshop;


    // JWT 로그인을 위한 생성자
    public User(String userId, String userPassword){
        this.userId = userId;
        this.userPassword = userPassword;
    }


    public List<String> getRoleList(){
        if(this.roles.length() > 0){
            return Arrays.asList(this.roles.split(","));
        }

        return new ArrayList<>();
    }

    @Builder
    public User(Long id, String userId, String userPassword
            , String userName, String roles, String userMyshop){
        this.id = id;
        this.userId = userId;
        this.userPassword = userPassword;
        this.userName = userName;
        this.userAt = new Date();
        this.roles = roles;
        this.userMyshop = userMyshop;
    }
}
