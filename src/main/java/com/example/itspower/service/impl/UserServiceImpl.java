package com.example.itspower.service.impl;

import com.example.itspower.exception.ErrorCode;
import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.entity.UserGroupEntity;
import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.repository.UserGroupRepository;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.request.userrequest.UserUpdateRequest;
import com.example.itspower.response.UserResponseSave;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
            throw new ResourceNotFoundException(HttpStatus.FOUND.value(), "User is exits", HttpStatus.FOUND.name());
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

    @Override
    @Transactional
    public UserResponseSave update(UserUpdateRequest userUpdateRequest) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUserLogin(userUpdateRequest.getUserLogin());
            if (userEntity.isEmpty()) {
                throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "User not is exits", HttpStatus.BAD_REQUEST.name());
            }
            UserEntity user = new UserEntity();
            user.setId(userUpdateRequest.getId());
            user.setUserLogin(userUpdateRequest.getUserLogin());
            user.setPassword(passwordEncoder.encode(userUpdateRequest.getPassword()));
            user.setEdit(userUpdateRequest.isEdit());
            user.setView(userUpdateRequest.isView());
            user.setReport(userUpdateRequest.isReport());
            user.setAdmin(userUpdateRequest.isAdmin());
            user = userRepository.save(user);
            GroupEntity groupEntity = groupRoleRepository.save(userUpdateRequest.getGroupName(), userUpdateRequest.getParentId());
            UserGroupEntity userGroupEntity = userGroupRepository.save(user.getId(), groupEntity.getId());
            return new UserResponseSave(user, groupEntity, userGroupEntity);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }

    @Override
    public void delete(List<Integer> ids) {
        try {
            for (int userId : ids) {
                Optional<UserGroupEntity> userGroupEntity = userGroupRepository.finByUserId(userId);
                if (userGroupEntity.isPresent()) {
                    groupRoleRepository.deleteGroupRole(userGroupEntity.get().getGroupId());
                    userGroupRepository.deleteGroupUser(userId);
                }
            }
            userRepository.deleteIds(ids);
        } catch (Exception e) {
            throw new ResourceNotFoundException(ErrorCode.UNKNOWN_SERVER_ERROR);
        }
    }

    public UserDto loginInfor(String userLogin) {
        return userRepository.loginInfor(userLogin);
    }
}
