package com.example.itspower.controller;

import com.example.itspower.response.request.ReportEmpNumRequest;
import com.example.itspower.response.request.ReportRequest;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @PostMapping("/report/save")
    public ResponseEntity<Object> report(@Valid @RequestBody ReportRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reportService.add(1, request));
    }

    @PostMapping("/report/rest-num")
    public ResponseEntity<Object> reportRestNum(@RequestParam("userGroupId") int userGroupId,
                                                @RequestParam("id") int id,
                                                @RequestParam("restNum") int restNum) {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.reportRestNum(userGroupId, id, restNum));
    }

    @PostMapping("/report/emp-reason")
    public ResponseEntity<Object> reportEmpReason(@RequestBody List<@Valid ReportEmpNumRequest> requests) {
        reportService.reportEmpAndReason(requests, 1);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
    @GetMapping("/report/groupsName")
    public ResponseEntity<Object> getListUserGroup() {
        return ResponseEntity.status(HttpStatus.OK).body(reportService.getListGroup());
    }
}
