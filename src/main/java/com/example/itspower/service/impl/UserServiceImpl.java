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
import com.example.itspower.response.user.UserResponseSave;
import com.example.itspower.response.search.UserRequest;
import com.example.itspower.service.UserService;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final GroupRoleRepository groupRoleRepository;
    private final ReportRepository reportRepository;
    private final RestRepository restRepository;
    private final RiceRepository riceRepository;
    private final TransferRepository transferRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserGroupRepository userGroupRepository;
    private final UserLoginConfig userLoginConfig;

    @Override
    public ResponseEntity<SuccessResponse<Object>> save(UserRequest userRequest) {
        try {
            Optional<UserEntity> userEntity = userRepository.findByUserLogin(userRequest.getUserLogin());
            if (userEntity.isPresent()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "UserLogin is exits", null));
            }
            Optional<GroupEntity> groupEntity = groupRoleRepository.findByGroupName(userRequest.getGroupName());

            UserEntity user = new UserEntity();
            user.setUserLogin(userRequest.getUserLogin());
            user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
            user.setEdit(userRequest.isEdit());
            user.setView(userRequest.isView());
            user.setReport(userRequest.isReport());
            user.setAdmin(userRequest.isAdmin());
            user = userRepository.save(user);
            UserGroupEntity userGroupEntity = new UserGroupEntity();
            if (groupEntity.isPresent()) {
                userGroupRepository.save(user.getId(), groupEntity.get().getId());
            }
            UserResponseSave save = new UserResponseSave(user, groupEntity, userGroupEntity);
            return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.CREATED.value(), "register success", save));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "register not success", null));
        }
    }

    // chua can toi
    @Override
    @Transactional
    public ResponseEntity<Object> update(UserUpdateRequest userUpdateRequest, int id) {
        try {
            UserDetails userEntity = userLoginConfig.loadUserById(id);
            UserEntity user = new UserEntity();
            if (userEntity.getPassword().equals(userUpdateRequest.getPasswordOld())) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "password present is not correct", ""));
            }
            if (userUpdateRequest.getPasswordOld().equals(userUpdateRequest.getPassword())) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "new password is not current password present", HttpStatus.INTERNAL_SERVER_ERROR.name()));
            }
            Optional<UserGroupEntity> userGroupEntity = userGroupRepository.finByUserId(id);
            if (userGroupEntity.isEmpty()) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "user group is not exits", HttpStatus.INTERNAL_SERVER_ERROR.name()));
            }
            Optional<GroupEntity> groupEntity = groupRoleRepository.findById(userGroupEntity.get().getGroupId());
            if (groupEntity.isEmpty()) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "group Id is not exits", HttpStatus.INTERNAL_SERVER_ERROR.name()));
            }
            user.setId(id);
            user.setUserLogin(userEntity.getUsername());
            user.setPassword(userEntity.getPassword());
            user.setEdit(userUpdateRequest.isEdit());
            user.setView(userUpdateRequest.isView());
            user.setReport(userUpdateRequest.isReport());
            user.setAdmin(userUpdateRequest.isAdmin());
            user = userRepository.save(user);
            return ResponseEntity.ok().body(new UserResponseSave(user, groupEntity, userGroupEntity.get()));
        } catch (Exception e) {
            throw new ResourceNotFoundException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "", HttpStatus.INTERNAL_SERVER_ERROR.name());
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
