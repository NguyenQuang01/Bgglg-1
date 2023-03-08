package com.example.itspower.service;

import com.example.itspower.model.UserResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.response.search.UserAulogin;

public interface UserService {
    UserEntity save(AddToUserForm userForm);

    UserResponse loginUser(UserAulogin userAulogin);
}
