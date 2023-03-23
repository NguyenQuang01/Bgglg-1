package com.example.itspower.controller;

import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("transfer/now-date")
    @CrossOrigin
    public ResponseEntity<Object> nowDate() {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findAll());
    }

    @GetMapping("transfer/group")
    @CrossOrigin
    public ResponseEntity<Object> transferGroup(@RequestParam("groupId") int groupId,
                                                @RequestParam("transferDate") String transferDate,
                                                @RequestParam("transferId") int transferId,
                                                @RequestParam("type") int type) {
        transferService.updateTransferGroup(true, groupId, transferDate, transferId, type);
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "transfer success", null));
    }
}
