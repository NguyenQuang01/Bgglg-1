package com.example.itspower.repository;

import com.example.itspower.model.entity.UserGroupEntity;
import com.example.itspower.repository.repositoryjpa.UserGroupJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserGroupRepository {
    @Autowired
    private UserGroupJpaRepository userGroupJpaRepository;

    public void deleteGroupUser(Integer userId) {
        userGroupJpaRepository.deleteGroupUser(userId);
    }

    public Optional<UserGroupEntity> finByUserId(int userId) {
        return userGroupJpaRepository.findByUserId(userId);
    }

    public UserGroupEntity save(int userId, int groupId) {
        UserGroupEntity entity = new UserGroupEntity();
        entity.setUserId(userId);
        entity.setGroupId(groupId);
        return userGroupJpaRepository.save(entity);
    }
}
