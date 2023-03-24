package com.example.itspower.service.impl;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.exception.ResourceNotFoundException;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.ReportEntity;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.entity.UserGroupEntity;
import com.example.itspower.model.resultset.UserDto;
import com.example.itspower.repository.*;
import com.example.itspower.request.userrequest.UserUpdateRequest;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.UserResponseSave;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRoleRepository groupRoleRepository;
    @Autowired
    private ReportRepository reportRepository;
    @Autowired
    private RestRepository restRepository;
    @Autowired
    private RiceRepository riceRepository;
    @Autowired
    private TransferRepository transferRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserGroupRepository userGroupRepository;
    @Autowired
    private UserLoginConfig userLoginConfig;

    @Override
    public ResponseEntity<SuccessResponse<Object>> save(UserRequest userRequest) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUserLogin(userRequest.getUserLogin());
            if (userEntity.isPresent()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "UserLogin is exits", null));
            }
            UserEntity user = new UserEntity();
            user.setUserLogin(userRequest.getUserLogin());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setEdit(userRequest.isEdit());
            user.setView(userRequest.isView());
            user.setReport(userRequest.isReport());
            user.setAdmin(userRequest.isAdmin());
            user = userRepository.save(user);
            GroupEntity groupEntity = groupRoleRepository.update(userRequest.getGroupId(), userRequest.getGroupName(), userRequest.getParentId());
            UserGroupEntity userGroupEntity = userGroupRepository.save(user.getId(), groupEntity.getId());
            return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(HttpStatus.CREATED.value(), "register success", new UserResponseSave(user, groupEntity, userGroupEntity)));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "register not success", null));
        }
    }

    @Override
    @Transactional
    public UserResponseSave update(UserUpdateRequest userUpdateRequest, int id) {
        try {
            UserDetails userEntity = userLoginConfig.loadUserById(id);
            UserEntity user = new UserEntity();
            user.setId(id);
            user.setUserLogin(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setEdit(userUpdateRequest.isEdit());
            user.setView(userUpdateRequest.isView());
            user.setReport(userUpdateRequest.isReport());
            user.setAdmin(userUpdateRequest.isAdmin());
            user = userRepository.save(user);
            Optional<UserGroupEntity> userGroupEntity = userGroupRepository.finByUserId(id);
            if (userGroupEntity.isEmpty()) {
                throw new ResourceNotFoundException(HttpStatus.BAD_REQUEST.value(), "", HttpStatus.BAD_REQUEST.name());
            }
            GroupEntity groupEntity = groupRoleRepository.update(userGroupEntity.get().getGroupId(), userUpdateRequest.getGroupName(), userUpdateRequest.getParentId());
            return new UserResponseSave(user, groupEntity, userGroupEntity.get());
        } catch (Exception e) {
            throw new ResourceNotFoundException(HttpStatus.BAD_GATEWAY.value(), "", HttpStatus.BAD_GATEWAY.name());
        }
    }

    @Override
    public void delete(List<Integer> ids, String userName) {
        try {
            UserDetails userDetails = userLoginConfig.loadUserByUsername("admin");
            if (userName.equals(userDetails.getUsername())) {
                for (int userId : ids) {
                    Optional<UserGroupEntity> userGroupEntity = userGroupRepository.finByUserId(userId);
                    if (userGroupEntity.isPresent()) {
                        Optional<ReportEntity> reportEntity = reportRepository.findByGroupId(userGroupEntity.get().getGroupId());
                        if (reportEntity.isPresent()) {
                            restRepository.deleteRestReportId(reportEntity.get().getId());
                            riceRepository.deleteReportId(reportEntity.get().getId());
                            transferRepository.deleteTransferReportId(reportEntity.get().getId());
                            reportRepository.deleteByGroupId(userGroupEntity.get().getGroupId());
                        }
                        userGroupRepository.deleteGroupUser(userId);
                    }
                }
                userRepository.deleteIds(ids);
            }
        } catch (Exception e) {
            throw new RuntimeException("delete not success");
        }
    }

    public boolean isCheckReport(int groupId) {
        Optional<ReportEntity> reportEntity = reportRepository.findByReportDateAndGroupId(DateUtils.formatDate(new Date()), groupId);
        return reportEntity.isPresent();
    }

    public UserDto loginInfor(String userLogin) {
        return userRepository.loginInfor(userLogin);
    }
}
