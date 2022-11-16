package com.example.itspower.controller;

import com.example.itspower.dto.search.AddToUserForm;
import com.example.itspower.entity.Employee;
import com.example.itspower.entity.RolesEntity;
import com.example.itspower.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RestController()
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
   private  UserService userService;
    @GetMapping("/getUser")
    public ResponseEntity<List<UserEntity>> getListUser(){
        List<UserEntity> user = userService.getUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/create")
    public SuccessResponse<Employee> createEmployee(@RequestBody UserEntity user) {
        try {
            userService.saveUser(user);
            return new SuccessResponse<>(1, "create success user", user.getId(), "insert.success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new SuccessResponse<>(0, "error create user :" + e, "insert.fail", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/role/create")
    public SuccessResponse<RolesEntity> saveRole(@RequestBody RolesEntity role) {
        try {
            userService.saveRole(role);
            return new SuccessResponse<>(1, "create success", role.getId(), "insert.success", HttpStatus.CREATED);
        } catch (Exception e) {
            return new SuccessResponse<>(0, "error create role :" + e, "insert.fail", HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/role/addtouser")
    public SuccessResponse<?> saveRoleToUser(@RequestBody AddToUserForm form) {
        try{
            userService.addRoleToUser(form.getUserName(),form.getRoleName());
            return new SuccessResponse<>(1, "add role to user ", null, "insert.success", HttpStatus.CREATED);
        }catch (Exception e){
            return new SuccessResponse<>(0, "add role to user fail : " +e, null, "insert.fail", HttpStatus.BAD_REQUEST);
        }
    }
}
