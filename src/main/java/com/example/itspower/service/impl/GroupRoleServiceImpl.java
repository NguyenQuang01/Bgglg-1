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
import org.springframework.util.CollectionUtils;

import java.util.*;
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
        List<ViewDetailGroupResponse> mapReportParent = groupRoleRepository.getDetailParent();
        List<ViewDetailGroupResponse> mapReport = groupRoleRepository.getDetails(reportDate);
        if (!CollectionUtils.isEmpty(mapReport)) {
            List<ViewDetailGroups> mapData = new ArrayList<>();
            List<ViewDetailGroups> root = new ArrayList<>();
            for (ViewDetailGroupResponse mapParent : mapReportParent) {
                mapData.add(new ViewDetailGroups(mapParent));
            }
            for (ViewDetailGroupResponse mapChildren : mapReport) {
                mapData.add(new ViewDetailGroups(mapChildren));
            }
            for (Integer parentId : groupRoleRepository.getParentId()) {
                root.add(mapData.stream().filter(map -> map.getKey().intValue() == parentId.intValue()).collect(Collectors.toList()).get(0));
            }
            float totalLaborReportsProductivity = 0;
            int totalRiseNumAll = 0;
            for (ViewDetailGroups viewDetailGroups : root) {
                int restNum = 0;
                float labor = 0;
                int partTime = 0;
                int studentNum = 0;
                int totalRiseChild = 0;
                List<ViewDetailGroups> children = mapData.stream().filter(z -> z.getParentId().intValue() == viewDetailGroups.getKey().intValue()).collect(Collectors.toList());
                for (ViewDetailGroups item : children) {
                    if (viewDetailGroups.getName().equals("Office") || viewDetailGroups.getName().equals("Văn phòng")) {
                        item.setEnterprise(null);
                    } else {
                        item.setOffice(null);
                    }
                    if (item.getParentId().intValue() == viewDetailGroups.getKey()) {
                        restNum += item.getNumberLeave();
                        labor += item.getLaborProductivity();
                        partTime += item.getPartTimeEmp();
                        studentNum += item.getStudentNum();
                        totalRiseChild += item.getTotalRiseNum();
                    }
                    item.setTotalRiseNum(null);
                }
                if (viewDetailGroups.getName().equals("Office") || viewDetailGroups.getName().equals("Văn phòng")) {
                    viewDetailGroups.setOffice((int) labor);
                    viewDetailGroups.setEnterprise(null);
                    viewDetailGroups.viewDetailGroups(restNum, labor, partTime, studentNum, null);
                } else {
                    viewDetailGroups.setEnterprise((int) labor);
                    viewDetailGroups.setOffice(null);
                    viewDetailGroups.viewDetailGroups(restNum, labor, partTime, studentNum, null);
                }
                totalLaborReportsProductivity += labor;
                totalRiseNumAll += totalRiseChild;
            }
            for (ViewDetailGroups viewDetail : root) {
                if (viewDetail.getName().equals("Office") || viewDetail.getName().equals("Văn Phòng")) {
                    viewDetail.setTotalRiseNum(totalRiseNumAll);
                    viewDetail.setRatio((float) Math.round(((viewDetail.getLaborProductivity() / totalLaborReportsProductivity) * 100) * 10) / 10);
                } else {
                    viewDetail.setRatio((float) Math.round(((viewDetail.getLaborProductivity() / totalLaborReportsProductivity) * 100) * 10) / 10);
                }
            }
            Map<Integer, List<ViewDetailGroups>> parentIdToChildren =
                    mapData.stream().collect(Collectors.groupingBy(ViewDetailGroups::getParentId));
            mapData.forEach(p -> p.setChildren(parentIdToChildren.get(p.getKey())));
            return new SuccessResponse<>(HttpStatus.OK.value(), "successfully", parentIdToChildren.get(0));
        }
        return new SuccessResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "that day has not been reported", null);
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
