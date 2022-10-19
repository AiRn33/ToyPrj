package com.toyprj.start.model;

import com.toyprj.start.entity.User;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
public class UserDto {

    private Long id;

    private String userId;

    private String userPassword;

    private String userName;

    private Date userAt;

    private String roles;

    private String userMyshop;
    @Builder
    public User toEntity(){
        return User.builder()
                .id(id)
                .userId(userId)
                .userPassword(userPassword)
                .userName(userName)
                .roles(roles)
                .userAt(new Date())
                .userMyshop(userMyshop)
                .build();
    }
}
