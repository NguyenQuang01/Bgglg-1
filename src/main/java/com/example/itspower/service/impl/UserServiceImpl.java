package com.example.itspower.service.impl;

import com.example.itspower.entity.RolesEntity;
import com.example.itspower.entity.UserEntity;
import com.example.itspower.repository.RolesRepository;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Transactional
@Slf4j

public class UserServiceImpl implements UserService , UserDetailsService {

    private  final PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    //save user to database
    @Override
    public UserEntity saveUser(UserEntity user) {
        log.info("saving new user to the database " + user.getUserName());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //save role to database
    @Override
    public RolesEntity saveRole(RolesEntity roles) {
        log.info("saving new role to the database " + roles.getName());
        return rolesRepository.save(roles);
    }

    // get user by userName
    @Override
    public UserEntity getUserByUserName(String userName) {
        log.info("get user by userNamee : " + userName);
        return userRepository.findByUserName(userName);
    }

    // add role to user
    @Override
    public void addRoleToUser(String userName, String roleName) {
        log.info("save role : " + roleName + " to user :" + userName);
        UserEntity user = userRepository.findByUserName(userName);
        RolesEntity roles = rolesRepository.findByName(roleName);
        if(roleName.equals("ROLE_USER")){
            roles.setName("ROLE_USER");
        }else if(roleName.equals("ROLE_MANAGER")){
            roles.setName("ROLE_MANAGER");
        }
        user.getRoles().add(roles);
    }

    // get list user
    @Override
    public List<UserEntity> getUser() {
        log.info("get list User");
        return userRepository.findAll();
    }
    //tim kiem va so sanh user name password and roles
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user =userRepository.findByUserName(username);
        if(user ==null){
            log.error("USER NOT FOUND IN THE DATABASE");
            throw  new UsernameNotFoundException("USER NOT FOUND IN THE DATABASE");
        }else{
            log.info("USER FOUND IN THE DATABASE");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getRoles().forEach(roles -> {authorities.add(new SimpleGrantedAuthority(roles.getName()));});
        return new User(user.getUserName(),user.getPassword(),authorities);
    }
}
