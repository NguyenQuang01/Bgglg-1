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
            GroupEntity groupEntity = groupRoleRepository.findById(userRequest.getGroupId());
            UserGroupEntity userGroupEntity = userGroupRepository.save(user.getId(), groupEntity.getId());
            return ResponseEntity.ok(new SuccessResponse<>(HttpStatus.CREATED.value(), "register success", new UserResponseSave(user, groupEntity, userGroupEntity)));
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
            user.setId(id);
            user.setUserLogin(userEntity.getUsername());
            if (userEntity.getPassword().equals(userUpdateRequest.getPasswordOld())) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "password present is not correct", ""));
            }
            if (userUpdateRequest.getPasswordOld().equals(userUpdateRequest.getPassword())) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "new password is not current password present", HttpStatus.BAD_REQUEST.name()));
            }
            user.setPassword(userEntity.getPassword());
            user.setEdit(userUpdateRequest.isEdit());
            user.setView(userUpdateRequest.isView());
            user.setReport(userUpdateRequest.isReport());
            user.setAdmin(userUpdateRequest.isAdmin());
            user = userRepository.save(user);
            Optional<UserGroupEntity> userGroupEntity = userGroupRepository.finByUserId(id);
            if (userGroupEntity.isEmpty()) {
                return ResponseEntity.badRequest().body(new SuccessResponse<>(HttpStatus.BAD_REQUEST.value(), "user group is not exits", HttpStatus.BAD_REQUEST.name()));
            }
            GroupEntity groupEntity = groupRoleRepository.findById(userGroupEntity.get().getGroupId());
            return ResponseEntity.ok().body(new UserResponseSave(user, groupEntity, userGroupEntity.get()));
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
