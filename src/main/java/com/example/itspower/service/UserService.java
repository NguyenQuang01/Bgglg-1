package com.example.itspower.service;

import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.response.UserResponseSave;
import com.example.itspower.response.search.UserRequest;

public interface UserService {
    UserResponseSave save(UserRequest userRequest);

    UserDto loginInfor(String userLogin);

}
