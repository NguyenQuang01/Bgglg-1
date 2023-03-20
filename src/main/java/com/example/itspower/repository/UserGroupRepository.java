package com.example.itspower.repository;

import com.example.itspower.model.entity.UserGroupEntity;
import com.example.itspower.repository.repositoryjpa.UserGroupJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserGroupRepository {
    @Autowired
    private UserGroupJpaRepository userGroupJpaRepository;

    public UserGroupEntity save(int userId, int groupId) {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setUserId(userId);
        entity.setGroupId(groupId);
        return userGroupJpaRepository.save(entity);
    }
}
