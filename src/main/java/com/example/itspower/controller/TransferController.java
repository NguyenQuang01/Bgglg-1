package com.example.itspower.controller;

import com.example.itspower.response.request.TransferRequest;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TransferController {

    @Autowired
    private TransferService transferService;


    @PostMapping("/transfer/save")
    public ResponseEntity<Object> save(@RequestBody List<TransferRequest> requests) {
        return ResponseEntity.status(HttpStatus.CREATED).body(transferService.saveTransfer(requests));
    }

}
