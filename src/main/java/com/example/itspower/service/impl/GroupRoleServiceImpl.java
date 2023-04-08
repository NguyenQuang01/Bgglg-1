package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.resultset.GroupRoleDto;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.request.GroupRoleRequest;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleResponse;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import com.example.itspower.response.group.ViewDetailGroups;
import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupRoleServiceImpl implements GroupRoleService {
    @Autowired
    private GroupRoleRepository groupRoleRepository;

    private static final String OFFICE = "Office";
    private static final String VANPHONG = "Văn phòng";
    private static final String DONVILE = "Đơn vị lẻ";
    private static final String HOC_SINH_CHUA_BAO_NANG_SUAT = "Học sinh chưa báo năng suất";
    private static final String THOI_VU_TO_MAY = "Thời vụ tổ may";

    @Override
    public List<GroupRoleResponse> searchAll() {
        return getSubListChildren(groupRoleRepository.searchAll());
    }

    public List<GroupRoleResponse> getSubListChildren(List<GroupRoleDto> groups) {
        List<GroupRoleResponse> groupRoleResponses = new ArrayList<>();
        groups.forEach(i -> {
            GroupRoleResponse groupRoleResponse = new GroupRoleResponse(i);
            groupRoleResponses.add(groupRoleResponse);
        });
        Map<Integer, List<GroupRoleResponse>> parentIdToChildren = groupRoleResponses.stream().collect(Collectors.groupingBy(GroupRoleResponse::getParentId));
        groupRoleResponses.forEach(p -> p.setChildren(parentIdToChildren.get(p.getId())));
        return parentIdToChildren.get(0);
    }

    @Override
    public Object getDetailsReport(String reportDate) {
        List<ViewDetailGroupResponse> mapReportParent = groupRoleRepository.getDetailParent();
        List<ViewDetailGroupResponse> mapReport = groupRoleRepository.getDetails(reportDate);
        List<ViewDetailGroups> mapData = new ArrayList<>();
        for (ViewDetailGroupResponse mapParent : mapReportParent) {
            mapData.add(new ViewDetailGroups(mapParent));
        }
        for (ViewDetailGroupResponse mapChildren : mapReport) {
            mapData.add(new ViewDetailGroups(mapChildren));
        }
        Map<Integer, List<ViewDetailGroups>> parentIdToChildren =
                mapData.stream().collect(Collectors.groupingBy(ViewDetailGroups::getParentId));
        mapData.forEach(p -> p.setChildren(parentIdToChildren.get(p.getKey())));
        return new SuccessResponse<>(HttpStatus.OK.value(), "report successfully!", parentIdToChildren.get(0));
    }


    @Override
    public List<GroupEntity> searchAllByParentId(int parentId) {
        return groupRoleRepository.findAllByParentId(parentId);
    }

    @Override
    public List<GroupEntity> searchAllByParentIdIsNull() {
        return groupRoleRepository.findAllByParentIdNotNull();
    }

    @Override
    public Object getDemarcationRes(Integer groupId) {
        return groupRoleRepository.getDemarcationRes(groupId);
    }

    @Override
    public Object updateGroupRole(String groupName, Integer demarcation,String parentName) {
        Optional<GroupEntity> groupCheck = groupRoleRepository.findByGroupName(groupName);
        if (groupCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "groupName sai ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<GroupEntity> groupChildren = groupRoleRepository.findByGroupName(parentName);
        if (groupCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "parentName sai ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        GroupEntity groupEntity = groupRoleRepository.update(groupChildren.get().getId(), parentName, groupCheck.get().getId(), demarcation);
        return new SuccessResponse<>(HttpStatus.OK.value(), "update group demarcation success", groupEntity);
    }

    @Override
    public void delete(String groupName,String parentName) {
        try {
            Optional<GroupEntity> optionalGroupEntity = groupRoleRepository.findByGroupName(groupName);
            if (optionalGroupEntity.isPresent()){
                groupRoleRepository.delete(parentName,optionalGroupEntity.get().getId());
            }else {
                groupRoleRepository.delete(parentName,null);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object save(GroupRoleRequest groupRoleRequest) {
        Optional<GroupEntity> groupCheck = groupRoleRepository.findByGroupName(groupRoleRequest.getGroupName());
        if (groupCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Group name not exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        Optional<GroupEntity> groupRoleCheck = groupRoleRepository.findByGroupName(groupRoleRequest.getGroupNameRoot());
        if (groupRoleCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Group name root is exist!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        GroupEntity groupEntity = groupRoleRepository.save(groupRoleRequest, groupRoleCheck.get().getId());
        return new SuccessResponse<>(HttpStatus.OK.value(), "Save group success!", groupEntity);
    }
}
