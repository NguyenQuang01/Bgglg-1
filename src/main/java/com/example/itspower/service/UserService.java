package com.example.itspower.service;

import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.request.userrequest.UserUpdateRequest;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.response.user.ListUserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    SuccessResponse<Object> save(UserRequest userRequest);

    ResponseEntity<Object> update(UserUpdateRequest userUpdateRequest, int id);

    UserDto loginInfor(String userLogin);

    void delete(List<Integer> ids, String userName);

    boolean isCheckReport(int groupId);
    List<ListUserResponse> getAllUser(String userName,String groupName);
}
