package com.example.itspower.repository;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GroupRoleRepository {
    @Autowired
    private GroupJpaRepository groupJpaRepository;

    public GroupEntity save(String groupName, int parentId) {
        GroupEntity entity = new GroupEntity();
        entity.setGroupName(groupName);
        entity.setParentId(parentId);
        return groupJpaRepository.save(entity);
    }
}
