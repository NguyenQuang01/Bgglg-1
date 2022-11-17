package com.example.itspower.controller;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.itspower.dto.search.AddToUserForm;
import com.example.itspower.entity.Employee;
import com.example.itspower.entity.RolesEntity;
import com.example.itspower.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static java.util.Arrays.stream;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@Controller
@RestController()
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
   private  UserService userService;
    @Autowired
    PasswordEncoder passwordEncoder;
    @GetMapping("/getUser")
    public ResponseEntity<List<UserEntity>> getListUser(){
        List<UserEntity> user = userService.getUser();
        return new ResponseEntity<>(user,HttpStatus.OK);
    }
    @PostMapping("/create")
    public SuccessResponse<Employee> createEmployee(@RequestBody UserEntity user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
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
