package com.example.itspower.controller;

import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GroupRoleController {
    @Autowired
    private GroupRoleService groupRoleService;
    @GetMapping("/groupRole")
    @CrossOrigin
    public ResponseEntity<Object> searchAll() {
        return ResponseEntity.status(HttpStatus.OK).body(groupRoleService.searchAll());
    }
    @GetMapping("/groupRoleDetails")
    @CrossOrigin
    public ResponseEntity<Object> searchDetails(@Param("parentId") int parentId) {
        return ResponseEntity.status(HttpStatus.OK).body(groupRoleService.searchAllByParentId(parentId));
    }

    @GetMapping("/groupRoleRoot")
    @CrossOrigin
    public ResponseEntity<Object> searchRoleRoot() {
        return ResponseEntity.status(HttpStatus.OK).body(groupRoleService.searchAllByParentIdIsNull());
    }

}
