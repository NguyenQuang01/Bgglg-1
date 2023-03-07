package com.example.itspower.controller;

import com.example.itspower.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.model.UserResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Controller
@RestController()
@Slf4j
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<SuccessResponse<Object>> save(@Valid @RequestBody AddToUserForm addToUserForm) {
        UserEntity data = userService.save(addToUserForm);
        return ResponseEntity.ok(new SuccessResponse<>("save success", data));
    }

    @PostMapping("/login")
    public ResponseEntity<SuccessResponse<Object>> login(@Valid @RequestBody UserAulogin userAulogin) {
        UserResponse response = userService.loginUser(userAulogin);
        return ResponseEntity.ok(new SuccessResponse<>(1, "login success", response));
    }
}
