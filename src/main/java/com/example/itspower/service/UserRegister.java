package com.example.itspower.service;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.response.search.AddToUserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegister implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserEntity save(AddToUserForm userForm) {
        UserEntity user = new UserEntity();
        user.setUserLogin(userForm.getUserLogin());
        user.setUserName(userForm.getUserName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return userRepository.save(user);
    }
}
