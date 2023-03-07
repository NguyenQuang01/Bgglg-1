package com.example.itspower.controller;

import com.example.itspower.dto.search.AddToUserForm;
import com.example.itspower.entity.UserEntity;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static java.util.Arrays.stream;

@Controller
@RestController()
@Slf4j
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @PostMapping("/save")
    public ResponseEntity<SuccessResponse<Object>> save(@Valid @RequestBody AddToUserForm addToUserForm) {
        UserEntity data = userService.save(addToUserForm);
        return ResponseEntity.ok(new SuccessResponse<>("save success", data));
    }
}
