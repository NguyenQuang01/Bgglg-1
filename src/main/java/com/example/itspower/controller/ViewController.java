package com.example.itspower.controller;

import com.example.itspower.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ViewService viewService;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<Object> getAll(@RequestParam("date") String date) {
        return ResponseEntity.status(HttpStatus.OK).body(viewService.getView(date));
    }
}
