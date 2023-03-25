package com.example.itspower.controller;

import com.example.itspower.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ViewService viewService;
    @GetMapping("/all")
    public ResponseEntity<Object> getAll(@RequestParam("date")String date){
        return ResponseEntity.status(HttpStatus.OK).body(viewService.getView(date));
    }
}
