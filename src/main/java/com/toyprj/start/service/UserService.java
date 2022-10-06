package com.toyprj.start.service;

import com.toyprj.start.entity.User;
import com.toyprj.start.model.UserDto;
import com.toyprj.start.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserJpaRepository userJpaRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void signup(String userId, String userPassword, String userName){

        UserDto dto = new UserDto();
        dto.setUserId(userId);
        dto.setUserPassword(bCryptPasswordEncoder.encode(userPassword));
        dto.setUserName(userName);
        dto.setRoles("ROLE_MEMBER");

        userJpaRepository.save(dto.toEntity());
    }

    public User getUser(String id){

        User user = userJpaRepository.findByuserId(id);

        return user;
    }

    public void updateUser(String name){

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails) principal;
        String id = userDetails.getUsername();

        User user = userJpaRepository.findByuserId(id);

        UserDto dto = new UserDto();
        dto.setUserName(name);
        dto.setRoles(user.getRoles());
        dto.setUserId(user.getUserId());
        dto.setUserPassword(user.getUserPassword());
        dto.setId(user.getId());

        userJpaRepository.save(dto.toEntity());
    }

    public void deleteUser(String id){

        Long ida = userJpaRepository.findByuserId(id).getId();

        userJpaRepository.deleteById(ida);
    }

    public void modifyPw(String pw,String id) {

        String password = bCryptPasswordEncoder.encode(pw);

        userJpaRepository.modifyByuserPassword(password,id);
    }

    public String findUser(String userName) {

        String userId = userJpaRepository.findByuserName(userName);

        return userId;
    }
}
