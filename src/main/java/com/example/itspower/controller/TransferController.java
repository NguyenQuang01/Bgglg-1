package com.example.itspower.controller;
import com.example.itspower.component.util.DateUtils;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Date;
@RestController
public class TransferController {
    @Autowired
    private TransferService transferService;

    @GetMapping("transfer/now-date")
    @CrossOrigin
    public ResponseEntity<Object> nowDate(@RequestParam("groupId") int groupId) {
        return ResponseEntity.status(HttpStatus.OK).body(transferService.findGroupIdAndTransferDate(groupId));
    }

    @GetMapping("transfer/accept")
    @CrossOrigin
    public ResponseEntity<Object> transferGroupAccept(@RequestParam("groupId") int groupId) {
        transferService.updateTransferGroup(true, groupId, DateUtils.formatDate(new Date()));
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "transfer success", null));
    }
}
