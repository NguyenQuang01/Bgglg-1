package com.example.itspower.controller;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;


    @PostMapping("/api/save")
    public ResponseEntity<Object> saveData(@RequestBody AddToUserForm addToUserForm) {
        try {
            UserEntity data = userService.save(addToUserForm);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(1, "register success", data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@RequestBody UserAulogin userAulogin) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(1, "login success", userService.loginUser(userAulogin)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
