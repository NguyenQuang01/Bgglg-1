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

import javax.validation.Valid;

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
        try {
            return ResponseEntity.ok(reportService.save(reportRequest, groupId));
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @PostMapping("/report/update")
    @CrossOrigin
    public ResponseEntity<Object> update(@Valid @RequestBody ReportRequest reportRequest, @RequestParam("groupId") int groupId) throws GeneralException {
        try {
            return ResponseEntity.ok(reportService.update(reportRequest, groupId));
        } catch (Exception e) {
            throw new GeneralException(e.getMessage());
        }
    }

    @PostMapping("/report/delete-rest")
    @CrossOrigin
    public ResponseEntity<Object> deleteRest(@RequestBody RestRequestDelete reportRequest) {
        reportService.deleteRestIdsAndReportId(reportRequest.getReportId(), reportRequest.getRestIds());
        return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.OK.value(), "delete success"));
    }
}
