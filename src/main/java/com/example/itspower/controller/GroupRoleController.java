package com.example.itspower.controller;

import com.example.itspower.exception.ReasonException;
import com.example.itspower.request.GroupRoleRequest;
import com.example.itspower.response.BaseResponse;
import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.itspower.component.enums.StatusReason.ERROR;
import static com.example.itspower.component.enums.StatusReason.SUCCESS;

@RestController
public class GroupRoleController {
    @Autowired
    private GroupRoleService groupRoleService;

    @GetMapping("/groupRole")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> searchAll() {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, groupRoleService.searchAll());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
    }

    @GetMapping("/getName")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> getName() {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, groupRoleService.getName());
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
    }

    @GetMapping("/groupRole/save")
    @CrossOrigin
    public ResponseEntity<BaseResponse<Object>> save(@RequestBody GroupRoleRequest groupRoleRequest) {
        try {
            BaseResponse<Object> res = new BaseResponse<>(HttpStatus.CREATED.value(), SUCCESS, groupRoleService.save(groupRoleRequest));
            return ResponseEntity.status(HttpStatus.OK).body(res);
        } catch (Exception e) {
            throw new ReasonException(HttpStatus.BAD_REQUEST.value(), ERROR, e);
        }
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
    public ResponseEntity<Object> update(@RequestParam("groupName") String groupName, @RequestParam("parentName") String parentName, @RequestParam("demarcation") Integer demarcation) {
        try {
            return ResponseEntity.ok(groupRoleService.updateGroupRole(groupName, demarcation, parentName));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/groupRole/delete")
    @CrossOrigin
    public ResponseEntity<Object> delete(@RequestParam("groupName") String groupName, @RequestParam("parentName") String parentName) {
        try {
            groupRoleService.delete(groupName, parentName);
            return ResponseEntity.ok("Thanh cong");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/groupRole/view-root")
    @CrossOrigin
    public ResponseEntity<Object> viewRoot() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(groupRoleService.getViewRoot());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

}
