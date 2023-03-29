package com.example.itspower.repository;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleDemarcationRes;
import com.example.itspower.response.group.GroupRoleResponse;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<GroupEntity> findById(Integer groupRoleId) {
        return groupJpaRepository.findById(groupRoleId);
    }

    public List<GroupRoleResponse> searchAll() {
        return getSubListChirdlen(groupJpaRepository.findAll());
    }

    public  List<ViewDetailGroupResponse> getDetails() {
        List<ViewDetailGroupResponse> mapReport = groupJpaRepository.getDetail();
        return mapReport;
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

    public List<GroupRoleResponse> getSubListChirdlen(List<GroupEntity> groups) {
        List<GroupRoleResponse> groupRoleResponses = new ArrayList<>();
        groups.forEach(i -> {
            GroupRoleResponse groupRoleResponse = new GroupRoleResponse(i);
            groupRoleResponses.add(groupRoleResponse);
        });
        Map<Integer, List<GroupRoleResponse>> parentIdToChildren = groupRoleResponses.stream().collect(Collectors.groupingBy(GroupRoleResponse::getParentId));
        groupRoleResponses.forEach(p -> p.setGroups(parentIdToChildren.get(p.getId())));
        return parentIdToChildren.get(0);
    }

    public Object getDemarcationRes(Integer groupId) {
        Optional<GroupEntity> groupEntity = groupJpaRepository.findById(groupId);
        if (groupEntity.isPresent()) {
            return new SuccessResponse<>(HttpStatus.OK.value(), "success", new GroupRoleDemarcationRes(groupEntity.get()));
        }
        return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "group id is not exits", HttpStatus.INTERNAL_SERVER_ERROR.name());
    }
}
