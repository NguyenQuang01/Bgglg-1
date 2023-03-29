package com.example.itspower.repository;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleDemarcationRes;
import com.example.itspower.response.group.GroupRoleResponse;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import com.example.itspower.response.group.ViewDetailGroups;
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

    public Object getDetails() {
        List<ViewDetailGroupResponse> mapReport = groupJpaRepository.getDetail();
        List<ViewDetailGroups> mapData = new ArrayList<>();
        List<ViewDetailGroups> root = new ArrayList<>();
        for (ViewDetailGroupResponse map : mapReport) {
            mapData.add(new ViewDetailGroups(map));
        }
        for (Integer parentId : groupJpaRepository.getAllParentId()) {
            root.add(mapData.stream().filter(map -> map.getKey().intValue() == parentId.intValue()).collect(Collectors.toList()).get(0));
        }
        for (ViewDetailGroups viewDetailGroups : root) {
            int demarcation = 0;
            int restNum = 0;
            int labor = 0;
            int partTime = 0;
            int studentNum = 0;
            int riceNum = 0;
            List<ViewDetailGroups> children = mapData.stream().filter(z -> z.getParentId().intValue() == viewDetailGroups.getKey()).collect(Collectors.toList());
            for (ViewDetailGroups item : children) {
                if (item.getParentId().intValue() == viewDetailGroups.getKey()) {
                    demarcation += item.getEnterprise();
                    restNum += item.getNumberLeave();
                    labor += item.getLaborProductivity();
                    partTime += item.getPartTimeEmp();
                    studentNum += item.getStudentNum();
                    riceNum += item.getRiceVip() + item.getRiceEmp() + item.getRiceCus();
                }
            }
            if (viewDetailGroups.getName().equals("office")) {
                viewDetailGroups.setOffice(demarcation);
            } else {
                viewDetailGroups.setEnterprise(demarcation);
            }
            viewDetailGroups.setNumberLeave(restNum);
            viewDetailGroups.setTotalLaborProductivity(labor);
            viewDetailGroups.setPartTimeEmp(partTime);
            viewDetailGroups.setStudentNum(studentNum);
            viewDetailGroups.setNumberRice(riceNum);
        }
        Map<Integer, List<ViewDetailGroups>> parentIdToChildren =
                mapData.stream().collect(Collectors.groupingBy(ViewDetailGroups::getParentId));
        mapData.forEach(p -> p.setChildren(parentIdToChildren.get(p.getKey())));
        return parentIdToChildren.get(0);
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
