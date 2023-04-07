package com.example.itspower.repository;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.resultset.GroupRoleDto;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleDemarcationRes;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class GroupRoleRepository {
    @Autowired
    private GroupJpaRepository groupJpaRepository;

    public GroupEntity update(Integer groupRoleId, String groupName, Integer parentId, Integer demarcation) {
        GroupEntity entity = new GroupEntity();
        entity.setId(groupRoleId);
        entity.setGroupName(groupName);
        entity.setParentId(parentId);
        entity.setDemarcationAvailable(demarcation);
        return groupJpaRepository.save(entity);
    }

    public void delete(Integer groupRoleId) {
         groupJpaRepository.deleteById(groupRoleId);
    }

    public GroupEntity save(String groupName, Integer parentId, Integer demarcation) {
        GroupEntity entity = new GroupEntity();
        entity.setGroupName(groupName);
        entity.setParentId(parentId);
        entity.setDemarcationAvailable(demarcation);
        return groupJpaRepository.save(entity);
    }

    public Optional<GroupEntity> findById(Integer groupRoleId) {
        return groupJpaRepository.findById(groupRoleId);
    }

    public List<GroupRoleDto> searchAll() {
        return groupJpaRepository.findAllRole();
    }

    public List<ViewDetailGroupResponse> getDetails(String reportDate) {
        return groupJpaRepository.getDetail(reportDate);
    }

    public List<ViewDetailGroupResponse> getDetailParent() {
        return groupJpaRepository.getDetailParent();
    }

    public List<Integer> getParentId() {
        return groupJpaRepository.getAllParentId();
    }

    public List<GroupEntity> findAllByParentId(int parentId) {
        return groupJpaRepository.findAllByParentId(parentId);
    }

    public List<GroupEntity> findAllByParentIdNotNull() {
        return groupJpaRepository.findAllByParentIdIsNull();
    }

    public Object getDemarcationRes(Integer groupId) {
        Optional<GroupEntity> groupEntity = groupJpaRepository.findById(groupId);
        if (groupEntity.isPresent()) {
            return new SuccessResponse<>(HttpStatus.OK.value(), "success", new GroupRoleDemarcationRes(groupEntity.get()));
        }
        return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "group id is not exits", HttpStatus.INTERNAL_SERVER_ERROR.name());
    }

    public Optional<GroupEntity> findByGroupName(String groupName) {
        return groupJpaRepository.findByGroupName(groupName);
    }
}
