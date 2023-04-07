package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.response.group.GroupRoleResponse;

import java.util.List;

public interface GroupRoleService {
    List<GroupRoleResponse> searchAll();

    Object getDetailsReport(String reportDate);

    List<GroupEntity> searchAllByParentId(int parentId);

    List<GroupEntity> searchAllByParentIdIsNull();

    Object getDemarcationRes(Integer groupId);

    Object updateGroupRole(String groupName, Integer demarcation);

    void delete(Integer groupId);

    Object save(String groupName,Integer parentId, Integer demarcation);
}
