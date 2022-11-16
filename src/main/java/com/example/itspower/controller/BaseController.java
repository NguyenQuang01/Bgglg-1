package com.example.itspower.controller;

import com.example.itspower.exception.GlobalExceptionHandler;
import com.example.itspower.response.ResponseHandler;
import com.example.itspower.service.BaseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
public abstract class BaseController<T, ID> {
    protected BaseService<T, ID> baseService;

    public BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }
    @GetMapping("/get-all")
    public ResponseEntity<Object> getAll() {
        try {
            return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getAll());
        } catch (Exception e) {
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "cannot select + name object", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Object> getById(@PathVariable("id") ID id)   {
        if(id == null){
            return new ResponseEntity<>("Cannot find base with id=" + id, HttpStatus.BAD_REQUEST);
        }else{
            return ResponseHandler.generateResponse(HttpStatus.OK, "", baseService.getById(id));
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody T t) {
        return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.creatOrUpdate(null, t));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable("id") ID id,
                                         @Valid @RequestBody T t) {
        try{
            return ResponseHandler.generateResponse(HttpStatus.CREATED, "", baseService.creatOrUpdate(id, t));
        }catch (Exception e){
            return ResponseHandler.generateResponse(HttpStatus.BAD_REQUEST, "Insert fail", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") ID id) {
        return ResponseHandler.generateResponse(HttpStatus.ACCEPTED, "", baseService.deleteById(id));
    }
}
