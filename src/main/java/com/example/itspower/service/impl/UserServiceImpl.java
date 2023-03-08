package com.example.itspower.service.impl;

import com.example.itspower.filter.JwtToken;
import com.example.itspower.model.UserResponse;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserLoginConfig userLoginConfig;

    @Override
    public UserResponse loginUser(UserAulogin userAulogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAulogin.getUserLogin(), userAulogin.getPassword()));
        UserDetails userDetails = userLoginConfig.loadUserByUsername(userAulogin.getUserLogin());
        String token = jwtToken.generateToken(userDetails);
        return new UserResponse(userDetails.getUsername(), token);
    }

    @Override
    public UserEntity save(AddToUserForm userForm) {
        UserEntity user = new UserEntity();
        user.setUserLogin(userForm.getUserLogin());
        user.setUserName(userForm.getUserName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return userRepository.save(user);
    }
}
