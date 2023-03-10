package com.example.itspower.service.impl;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class UserLoginConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUser(username);
        if (user.getUserLogin() == null) {
            throw new UsernameNotFoundException("msg_userLoginNotExits_0");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(user.getUserLogin(), user.getPassword(), authorities);
    }
}
