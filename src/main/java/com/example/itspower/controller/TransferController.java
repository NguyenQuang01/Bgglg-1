package com.example.itspower.controller;

import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("transfer/now-date")
    public ResponseEntity<Object> nowDate() {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findAll());
    }

    @GetMapping("transfer/group")
    public ResponseEntity<Object> transferGroup(@RequestParam("groupId") int groupId,
                                                @RequestParam("transferDate") String transferDate) {
        transferService.updateTransferGroup(true, groupId, transferDate);
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "transfer success", null));
    }
}
