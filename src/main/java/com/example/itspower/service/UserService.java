package com.example.itspower.service;

import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.request.userrequest.UserUpdateRequest;
import com.example.itspower.response.UserResponseSave;
import com.example.itspower.response.search.UserRequest;

import java.util.List;

public interface UserService {
    UserResponseSave save(UserRequest userRequest);

    UserResponseSave update(UserUpdateRequest userUpdateRequest);

    UserDto loginInfor(String userLogin);

    void delete(List<Integer> ids);

}
