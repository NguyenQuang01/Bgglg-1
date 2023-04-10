package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.request.GroupRoleRequest;
import com.example.itspower.response.group.GroupRoleResponse;

import java.util.List;

public interface GroupRoleService {
    List<GroupRoleResponse> searchAll();

    List<GroupRoleResponse> searchAllDeleteTM();

    List<GroupEntity> searchAllByParentId(int parentId);

    List<GroupEntity> searchAllByParentIdIsNull();

    List<String> getName();

    Object getDemarcationRes(Integer groupId);

    Object updateGroupRole(Integer id, Integer demarcation);

    void delete(String groupNam, String parentName);

    Object save(GroupRoleRequest groupRoleRequest);

    Object getViewRoot();
}
