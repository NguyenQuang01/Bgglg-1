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
import java.util.Optional;

@Component
@Slf4j
public class UserLoginConfig implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> login = userRepository.findByUserLogin(username);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException("user login khong ton tai");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(login.get().getUserLogin(), login.get().getPassword(), authorities);
    }

    public UserDetails loadUserById(int id) throws UsernameNotFoundException {
        Optional<UserEntity> login = userRepository.findByUserId(id);
        if (login.isEmpty()) {
            throw new UsernameNotFoundException("user login khong ton tai");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        return new User(login.get().getUserLogin(), login.get().getPassword(), authorities);
    }
}
