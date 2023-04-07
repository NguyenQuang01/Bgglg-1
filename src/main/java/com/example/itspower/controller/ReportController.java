package com.example.itspower.controller;

import com.example.itspower.exception.GeneralException;
import com.example.itspower.request.ReportRequest;
import com.example.itspower.request.RestRequestDelete;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ReportController {
    @Autowired
    private ReportService reportService;

    @GetMapping("/report")
    @CrossOrigin
    public Object report(@RequestParam("reportDate") String reportDate, @RequestParam("groupId") int groupId) {
        return reportService.reportDto(reportDate, groupId);
    }

    @PostMapping("/report/save")
    @CrossOrigin
    public ResponseEntity<Object> save(@Validated @RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) throws GeneralException {
        return reportService.save(reportRequest, groupId);
    }

    @PostMapping("/report/update")
    @CrossOrigin
    public ResponseEntity<Object> update(@Validated @RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) {
        return reportService.update(reportRequest, groupId);
    }

    @PostMapping("/report/delete-rest")
    @CrossOrigin
    public ResponseEntity<Object> deleteRest(@RequestBody RestRequestDelete reportRequest) {
        reportService.deleteRestIdsAndReportId(reportRequest.getReportId(), reportRequest.getRestIds());
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "delete success"));
    }
}
