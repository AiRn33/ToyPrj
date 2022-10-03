package com.toyprj.start.auth;

import com.toyprj.start.entity.User;
import com.toyprj.start.repository.UserJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {


        User userEntity = userJpaRepository.findByuserId(userId);

        if(userEntity != null){
            return new PrincipalDetail(userEntity);
        }
        return null;
    }
}
