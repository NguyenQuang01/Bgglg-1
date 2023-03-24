package com.example.itspower.controller;


import com.example.itspower.exception.ReasonException;
import com.example.itspower.request.ReasonRequest;
import com.example.itspower.response.BaseResponse;
import com.example.itspower.service.ReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.itspower.component.enums.StatusReason.*;

@RestController
public class ReasonController {
    @Autowired
    private ReasonService reasonService;

    @GetMapping("/reason")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> searchALl() {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, reasonService.searchALl());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }

    }

    @PostMapping("/reason/save")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> save(@RequestBody List<ReasonRequest> reasonRequest) {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, reasonService.save(reasonRequest));
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }

    }

    @PutMapping("/reason/edit")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>>  edit(@RequestParam("id") int id, @RequestBody ReasonRequest reasonRequest) {
        try {
            if (reasonService.edit(reasonRequest,id) ==null){
                BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, null);
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }else {
                BaseResponse<Object> res = new BaseResponse<>(HttpStatus.OK.value(), SUCCESS, reasonService.edit(reasonRequest,id));
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }

    }

    @GetMapping("/reason/details")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>>  details(@RequestParam("id") int id) {
        try{
            if(reasonService.searchById(id).size() == 0){
                BaseResponse<Object> res = new BaseResponse<>(HttpStatus.OK.value(), "Reason no exit", reasonService.searchById(id));
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }else {
                BaseResponse<Object> res = new BaseResponse<>(HttpStatus.OK.value(), SUCCESS, reasonService.searchById(id));
                return ResponseEntity.status(HttpStatus.OK).body(res);
            }
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
    }

    @DeleteMapping("/reason/deleteALl")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>>  deleteAll() {
        try{
            reasonService.deleteAll();
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.OK.value(), SUCCESS, null);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }

    }
    @DeleteMapping("/reason/delete")
    @CrossOrigin
    public ResponseEntity<Object> delete(@RequestParam("id") int id) {
        try{
            reasonService.deleteById(id);
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.OK.value(), SUCCESS, null);
            return ResponseEntity.status(HttpStatus.OK).body(res);
        }catch (Exception e){
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
    }
}
