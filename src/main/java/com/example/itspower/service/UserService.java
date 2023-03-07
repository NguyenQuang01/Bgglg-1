package com.example.itspower.service;

import com.example.itspower.dto.search.AddToUserForm;
import com.example.itspower.entity.UserEntity;

import java.util.List;

public interface UserService {
    UserEntity save(AddToUserForm userForm);
}
