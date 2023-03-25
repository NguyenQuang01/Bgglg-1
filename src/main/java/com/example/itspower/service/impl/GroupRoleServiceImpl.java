package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleResponse;
import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupRoleServiceImpl implements GroupRoleService {
    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Override
    public List<GroupRoleResponse> searchAll() {
        return groupRoleRepository.searchAll();
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
    public Object updateGroupRole(Integer groupRoleId, Integer demarcation) {
        Optional<GroupEntity> groupCheck = groupRoleRepository.findById(groupRoleId);
        if (groupCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "group id is empty or null ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        GroupEntity groupEntity = groupRoleRepository.update(groupRoleId, groupCheck.get().getGroupName(), groupCheck.get().getParentId(), demarcation);
        return new SuccessResponse<>(HttpStatus.OK.value(), "update group demarcation success", groupEntity);
    }
}
