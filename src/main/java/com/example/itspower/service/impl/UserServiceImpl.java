package com.example.itspower.service.impl;

import com.example.itspower.entity.UserEntity;
import com.example.itspower.filter.JwtToken;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.response.model.UserResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;

    public UserResponse loginUser(UserAulogin login) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUserLogin(), login.getPassword()));
        UserDetails userDetails = loadUserByUsername(login.getUserLogin());
        String token = jwtToken.generateToken(userDetails);
        return new UserResponse(userDetails.getUsername(), token);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByUser(username);
        if (StringUtils.isEmpty(user.getUserLogin())) {
            throw new UsernameNotFoundException("msg_userLoginNotExits_0");
        }
        return new User(user.getUserName(), user.getPassword(), new ArrayList<>());
    }

    @Override
    public UserEntity save(AddToUserForm userForm) {
        UserEntity user = new UserEntity();
        user.setUserLogin(userForm.getUserLogin());
        user.setUserName(userForm.getUserName());
        user.setPassword(bCryptPasswordEncoder.encode(userForm.getPassword()));
        return userRepository.save(user);
    }
}
