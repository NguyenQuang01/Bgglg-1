package com.example.itspower.controller;

import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/groupRole/demarcation")
    @CrossOrigin
    public ResponseEntity<Object> getByDemarcation(@RequestParam("groupId") Integer groupId) {
        try {
            return ResponseEntity.ok(groupRoleService.getDemarcationRes(groupId));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/groupRole/update")
    @CrossOrigin
    public ResponseEntity<Object> update(@RequestParam("groupId") Integer groupId,
                                         @RequestParam("demarcation") Integer demarcation) {
        try {
            return ResponseEntity.ok(groupRoleService.updateGroupRole(groupId, demarcation));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
