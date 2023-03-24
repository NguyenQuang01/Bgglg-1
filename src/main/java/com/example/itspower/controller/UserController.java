package com.example.itspower.controller;

import com.example.itspower.filter.JwtToken;
import com.example.itspower.model.UserResponse;
import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.request.userrequest.UserDeleteRequest;
import com.example.itspower.request.userrequest.UserUpdateRequest;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.search.UserAulogin;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.service.UserService;
import com.example.itspower.service.impl.UserLoginConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<SuccessResponse<Object>> saveData(@Validated @RequestBody UserRequest userRequest) {
        return userService.save(userRequest);

    }

    @PostMapping("/api/update")
    public ResponseEntity<Object> update(@Valid @RequestBody UserUpdateRequest userRequest, @RequestParam("userId") int id) {
        return userService.update(userRequest, id);
    }

    @PostMapping("/api/delete")
    public ResponseEntity<Object> delete(@Validated @RequestBody UserDeleteRequest request) {
        userService.delete(request.getIds(), request.getUserLogin());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), "delete success", ""));
    }

    @PostMapping("/api/login")
    public ResponseEntity<Object> login(@Validated @RequestBody UserAulogin userAulogin) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userAulogin.getUserLogin(), userAulogin.getPassword()));
        UserDetails userDetails = userLoginConfig.loadUserByUsername(userAulogin.getUserLogin());
        String token = jwtToken.generateToken(userDetails);
        UserDto loginInfor = userService.loginInfor(userDetails.getUsername());
        boolean checkReport = userService.isCheckReport(loginInfor.getGroupId());
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>(HttpStatus.OK.value(), "login success", new UserResponse(userDetails.getUsername(), loginInfor, token, checkReport)));
    }
}
