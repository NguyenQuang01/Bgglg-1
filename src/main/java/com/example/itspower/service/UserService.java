package com.example.itspower.service;

import com.example.itspower.entity.RolesEntity;
import com.example.itspower.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity saveUser(UserEntity user);
    RolesEntity saveRole(RolesEntity roles);
    UserEntity getUserByUserName(String userName);
    void addRoleToUser(String userName,String roleName);
    List<UserEntity> getUser();
}
