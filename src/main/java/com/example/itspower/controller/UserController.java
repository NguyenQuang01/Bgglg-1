package com.example.itspower.controller;

import com.example.itspower.filter.JwtToken;
import com.example.itspower.filter.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.model.UserResponse;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.service.UserRegister;
import com.example.itspower.service.impl.UserServiceImpl;
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

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private JwtToken jwtToken;

    @Autowired
    private UserRegister userRegister;

    @PostMapping("/api/save")
    public ResponseEntity<SuccessResponse<Object>> saveData(@RequestBody AddToUserForm addToUserForm) {
        UserEntity data = userRegister.save(addToUserForm);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(1, "register success", data));
    }

    @PostMapping("/api/login")
    public ResponseEntity<SuccessResponse<Object>> login(@RequestBody UserAulogin userAulogin) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAulogin.getUserLogin(), userAulogin.getPassword()));
            UserDetails userDetails = userService.loadUserByUsername(userAulogin.getUserLogin());
            String token = jwtToken.generateToken(userDetails);
            return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(1, "login success", new UserResponse(userDetails.getUsername(), token)));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
