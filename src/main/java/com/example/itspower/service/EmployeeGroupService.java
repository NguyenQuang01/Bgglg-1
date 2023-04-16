package com.example.itspower.service;

import com.example.itspower.request.userrequest.addUserRequest;
import com.example.itspower.response.employee.EmployeeGroupResponse;

import java.util.List;

public interface EmployeeGroupService {
    void saveAll(List<addUserRequest> addUser);

    void delete(List<Integer> ids);

    List<EmployeeGroupResponse> getEmployee(Integer limit,String groupName,Integer groupId,String laborCode);
}
