package com.example.itspower.service.impl;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@Slf4j
public class UserLoginConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUser(username);
        if (user.getUserLogin()==null) {
            throw new UsernameNotFoundException("msg_userLoginNotExits_0");
        }
        return new User(user.getUserName(), user.getPassword(),new ArrayList<>());
    }
}
