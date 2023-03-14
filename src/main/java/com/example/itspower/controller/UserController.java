package com.example.itspower.controller;

import com.example.itspower.filter.JwtToken;
import com.example.itspower.model.UserResponse;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.UserService;
import com.example.itspower.service.impl.UserLoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserLoginConfig userLoginConfig;


    @PostMapping("/api/save")
    public ResponseEntity<Object> saveData(@Valid @RequestBody AddToUserForm addToUserForm) {
        try {
            UserEntity data = userService.save(addToUserForm);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(1, "register success", data));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@Valid @RequestBody UserAulogin userAulogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAulogin.getUserLogin(), userAulogin.getPassword()));
        UserDetails userDetails = userLoginConfig.loadUserByUsername(userAulogin.getUserLogin());
        String token = jwtToken.generateToken(userDetails);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(userDetails.getUsername(),token)));
    }
}
