package com.example.itspower.service;

import com.example.itspower.model.entity.GroupEntity;

import java.util.List;

public interface GroupRoleService {
    List<GroupEntity> searchAll();
    List<GroupEntity> searchAllByParentId(int parentId);
    List<GroupEntity> searchAllByParentIdIsNull();
}
