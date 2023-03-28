package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.response.group.GroupRoleAndReportDetailsRes;
import com.example.itspower.response.group.GroupRoleResponse;

import java.util.List;

public interface GroupRoleService {
    List<GroupRoleResponse> searchAll();
    List<GroupRoleAndReportDetailsRes> getDetailsReport();

    List<GroupEntity> searchAllByParentId(int parentId);

    List<GroupEntity> searchAllByParentIdIsNull();

    Object getDemarcationRes(Integer groupId);

    Object updateGroupRole(Integer groupRoleId, Integer demarcation);
}
