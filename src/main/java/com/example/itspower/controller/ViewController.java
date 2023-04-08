package com.example.itspower.controller;

import com.example.itspower.exception.ReasonException;
import com.example.itspower.response.BaseResponse;
import com.example.itspower.service.GroupRoleService;
import com.example.itspower.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.itspower.component.enums.StatusReason.ERROR;
import static com.example.itspower.component.enums.StatusReason.SUCCESS;

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ViewService viewService;

    @Autowired
    private GroupRoleService groupRoleService;

    @GetMapping("/all")
    @CrossOrigin
    public ResponseEntity<Object> getAll(@RequestParam("date") String date) {
        return ResponseEntity.status(HttpStatus.OK).body(viewService.getView(date));
    }

    @GetMapping("/groupRoleReport")
    @CrossOrigin
    public ResponseEntity<Object> getReport(@RequestParam("reportDate")String reportDate) {
        return ResponseEntity.status(HttpStatus.OK).body(groupRoleService.getDetailsReport(reportDate));
    }

    @GetMapping("/groupRoleViewDetails")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> searchAllViewDetails() {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, groupRoleService.searchAllView());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
    }
}
