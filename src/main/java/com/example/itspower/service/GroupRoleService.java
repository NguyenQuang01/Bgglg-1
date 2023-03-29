package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.response.group.GroupRoleResponse;

import java.util.List;

public interface GroupRoleService {
    List<GroupRoleResponse> searchAll();

    Object getDetailsReport();

    List<GroupEntity> searchAllByParentId(int parentId);

    List<GroupEntity> searchAllByParentIdIsNull();

    Object getDemarcationRes(Integer groupId);

    Object updateGroupRole(Integer groupRoleId, Integer demarcation);
}
