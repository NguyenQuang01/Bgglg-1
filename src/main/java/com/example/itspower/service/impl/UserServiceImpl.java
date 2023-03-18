package com.example.itspower.service.impl;

import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.entity.UserGroupEntity;
import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.repository.UserGroupRepository;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.response.UserResponseSave;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRoleRepository groupRoleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserGroupRepository userGroupRepository;

    @Override
    public UserResponseSave save(UserRequest userRequest) {
        Optional<UserEntity> userEntity = userRepository.findByUserLogin(userRequest.getUserLogin());
        if (userEntity.isPresent()) {
            throw new ResourceNotFoundException(HttpStatus.FOUND.value(), "User login is exits", HttpStatus.FOUND.name());
        }
        UserEntity user = new UserEntity();
        user.setUserLogin(userRequest.getUserLogin());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setEdit(userRequest.isEdit());
        user.setView(userRequest.isView());
        user.setReport(userRequest.isReport());
        user.setAdmin(userRequest.isAdmin());
        user = userRepository.save(user);
        GroupEntity groupEntity = groupRoleRepository.save(userRequest.getGroupName(), userRequest.getParentId());
        UserGroupEntity userGroupEntity = userGroupRepository.save(user.getId(), groupEntity.getId());
        return new UserResponseSave(user, groupEntity, userGroupEntity);
    }

    public UserDto loginInfor(String userLogin) {
        return userRepository.loginInfor(userLogin);
    }
}
