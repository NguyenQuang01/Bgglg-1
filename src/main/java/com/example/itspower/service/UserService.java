package com.example.itspower.service;

import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.model.entity.UserEntity;

public interface UserService {
    UserEntity save(AddToUserForm userForm);
}
