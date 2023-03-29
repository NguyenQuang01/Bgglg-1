package com.example.itspower.service.impl;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.response.SuccessResponse;
import com.example.itspower.response.group.GroupRoleResponse;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import com.example.itspower.response.group.ViewDetailGroups;
import com.example.itspower.service.GroupRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupRoleServiceImpl implements GroupRoleService {
    @Autowired
    private GroupRoleRepository groupRoleRepository;

    @Override
    public List<GroupRoleResponse> searchAll() {
        return groupRoleRepository.searchAll();
    }

    @Override
    public Object getDetailsReport(String reportDate) {
        List<ViewDetailGroupResponse> mapReport = groupRoleRepository.getDetails(reportDate);
        List<ViewDetailGroups> mapData = new ArrayList<>();
        List<ViewDetailGroups> root = new ArrayList<>();
        for (ViewDetailGroupResponse map : mapReport) {
            mapData.add(new ViewDetailGroups(map));
        }
        for (Integer parentId : groupRoleRepository.getParentId()) {
            root.add(mapData.stream().filter(map -> map.getKey().intValue() == parentId.intValue()).collect(Collectors.toList()).get(0));
        }
        for (ViewDetailGroups viewDetailGroups : root) {
            int demarcation = 0;
            int restNum = 0;
            int labor = 0;
            int partTime = 0;
            int studentNum = 0;
            int totalRiceNum = 0;
            int totalRiceCus = 0;
            int totalRiceVip = 0;
            int totalRiceEmp = 0;
            int totalLaborProductivity = 0;
            List<ViewDetailGroups> children = mapData.stream().filter(z -> z.getParentId().intValue() == viewDetailGroups.getKey()).collect(Collectors.toList());
            for (ViewDetailGroups item : children) {
                if (item.getParentId().intValue() == viewDetailGroups.getKey()) {
                    demarcation += item.getEnterprise();
                    restNum += item.getNumberLeave();
                    labor += item.getLaborProductivity();
                    partTime += item.getPartTimeEmp();
                    studentNum += item.getStudentNum();
                    totalRiceNum += item.getRiceVip() + item.getRiceEmp() + item.getRiceCus();
                    totalRiceCus += item.getRiceCus();
                    totalRiceVip += item.getRiceVip();
                    totalRiceEmp += item.getRiceEmp();
                    totalLaborProductivity += item.getTotalLaborProductivity();
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
            viewDetailGroups.setNumberRice(totalRiceNum);
            viewDetailGroups.setTotalLaborProductivity(totalLaborProductivity);
            viewDetailGroups.setRiceCus(totalRiceCus);
            viewDetailGroups.setRiceVip(totalRiceVip);
            viewDetailGroups.setRiceEmp(totalRiceEmp);
        }
        Map<Integer, List<ViewDetailGroups>> parentIdToChildren =
                mapData.stream().collect(Collectors.groupingBy(ViewDetailGroups::getParentId));
        mapData.forEach(p -> p.setChildren(parentIdToChildren.get(p.getKey())));
        return parentIdToChildren.get(0);
    }

    @Override
    public List<GroupEntity> searchAllByParentId(int parentId) {
        return groupRoleRepository.findAllByParentId(parentId);
    }

    @Override
    public List<GroupEntity> searchAllByParentIdIsNull() {
        return groupRoleRepository.findAllByParentIdNotNull();
    }

    @Override
    public Object getDemarcationRes(Integer groupId) {
        return groupRoleRepository.getDemarcationRes(groupId);
    }

    @Override
    public Object updateGroupRole(Integer groupRoleId, Integer demarcation) {
        Optional<GroupEntity> groupCheck = groupRoleRepository.findById(groupRoleId);
        if (groupCheck.isEmpty()) {
            return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "group id is empty or null ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        GroupEntity groupEntity = groupRoleRepository.update(groupRoleId, groupCheck.get().getGroupName(), groupCheck.get().getParentId(), demarcation);
        return new SuccessResponse<>(HttpStatus.OK.value(), "update group demarcation success", groupEntity);
    }
}
