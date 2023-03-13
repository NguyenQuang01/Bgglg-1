package com.example.itspower.controller;

import com.example.itspower.response.request.RestRequest;
import com.example.itspower.service.RestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RestsController {
    @Autowired
    private RestService restService;

    @PostMapping("/rest/save")
    public ResponseEntity<Object> save(@RequestBody List<RestRequest> requests, @RequestParam("reportId") Integer reportId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(restService.save(requests, reportId));
    }
}
