package com.example.itspower.controller;


import com.example.itspower.request.ReasonRequest;
import com.example.itspower.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReasonController {
    @Autowired
    private ReasonService reasonService;

    @GetMapping("/reason")
    @CrossOrigin
    public ResponseEntity<Object> searchALl() {
        return ResponseEntity.status(HttpStatus.OK).body(reasonService.searchALl());
    }

    @PostMapping("/reason/save")
    @CrossOrigin
    public ResponseEntity<Object> save(@RequestBody List<ReasonRequest> reasonRequest) {
        return ResponseEntity.status(HttpStatus.OK).body(reasonService.save(reasonRequest));
    }

    @PutMapping("/reason/edit")
    @CrossOrigin
    public ResponseEntity<Object> edit(@RequestParam("id") int id, @RequestBody ReasonRequest reasonRequest) {
        if (reasonService.edit(reasonRequest,id) ==null){
            return ResponseEntity.status(HttpStatus.OK).body("id no exits");
        }
        return ResponseEntity.status(HttpStatus.OK).body(reasonService.edit(reasonRequest,id));
    }

    @DeleteMapping("/reason/deleteALl")
    public ResponseEntity<Object> deleteAll() {
        reasonService.deleteAll();
        return ResponseEntity.status(HttpStatus.OK).body("thanh cong");
    }
    @DeleteMapping("/reason/delete")
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        reasonService.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("thanh cong");
    }
}
