package com.example.itspower.service.impl;

import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.model.resultset.ViewAllDto;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.group.ViewDetailGroups;
import com.example.itspower.service.ViewDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class ViewDetailSerivceImpl implements ViewDetailService {
    @Autowired
    ReportJpaRepository repository;
    @Autowired
    GroupJpaRepository groupJpaRepository;
    @Autowired
    private GroupRoleRepository groupRoleRepository;
    @Override
    public List<ViewDetailGroups> searchAllView(String reportDate) {
        List<RootNameDto> getIdRoot = groupJpaRepository.getAllRoot();
        List<ViewAllDto> viewAllDtoList = groupRoleRepository.searchAllView(reportDate);
        List<ViewAllDto> response =getLogicParent(viewAllDtoList,getIdRoot);
        int officeId = response.stream().filter(i->i.getGroupName().equalsIgnoreCase("văn phòng"))
                .map(i->i.getGroupId()).collect(Collectors.toList()).get(0);
        List<ViewDetailGroups> viewDetailsRes = new ArrayList<>();
        response.forEach(i -> {
            ViewDetailGroups viewDetailsResponse = new ViewDetailGroups(i,officeId);
            viewDetailsRes.add(viewDetailsResponse);
        });
        return children(viewDetailsRes);
    }
    List<ViewAllDto> getLogicParent (List<ViewAllDto> viewAllDtoList, List<RootNameDto> getIdRoot){
        Float totalLaborProductivity =Float.valueOf(String.valueOf(viewAllDtoList.stream().map(i ->i.getLaborProductivity())
                .mapToInt(Integer::intValue).sum())) ;
        List<ViewAllDto> office = viewAllDtoList.stream().filter(i->i.getGroupName()
                .equalsIgnoreCase("văn phòng")).collect(Collectors.toList());
        List< ViewAllDto> donViLe = viewAllDtoList.stream().filter(i->i.getGroupName()
                .equalsIgnoreCase("đơn vị lẻ")).collect(Collectors.toList());
        Float totalRatioOfOfficeAndDonvile = null;
        if(office.size() >0 && donViLe.size()>0){
            Float officeRatio = Float.valueOf(viewAllDtoList.stream().filter(i -> i.getGroupParentId()==office.get(0).getGroupId())
                    .map(i -> i.getLaborProductivity()).mapToInt(Integer::intValue).sum())/totalLaborProductivity*100;
            Float DonViLeRatio = Float.valueOf(viewAllDtoList.stream().filter(i -> i.getGroupParentId()==donViLe.get(0).getGroupId())
                    .map(i -> i.getLaborProductivity()).mapToInt(Integer::intValue).sum())/totalLaborProductivity*100;
             totalRatioOfOfficeAndDonvile = officeRatio+DonViLeRatio;
        }
        if(totalRatioOfOfficeAndDonvile.isNaN()==true){
            totalRatioOfOfficeAndDonvile=0.0f;
        }
        for(RootNameDto id :getIdRoot){
            List<ViewAllDto> parent = viewAllDtoList.stream().filter(i ->i.getGroupId()==id.getId()).collect(Collectors.toList());
            List<ViewAllDto> child = viewAllDtoList.stream().filter(i -> i.getGroupParentId()==id.getId()
                    ||i.getGroupId()==id.getId()).collect(Collectors.toList());
            Integer groupID = parent.stream().map(i ->i.getGroupId()).collect(Collectors.toList()).get(0);
            Integer groupParentId=parent.stream().map(i ->i.getGroupParentId()).collect(Collectors.toList()).get(0);
            String groupName=parent.stream().map(i ->i.getGroupName()).collect(Collectors.toList()).get(0);
            Integer reportDemarcation=child.stream().map(i ->i.getReportDemarcation()).mapToInt(Integer::intValue).sum();
            Float laborProductivity = 0.0f;
            laborProductivity=Float.valueOf(String.valueOf(child.stream().map(i ->
                    i.getLaborProductivity()).mapToInt(Integer::intValue).sum()));
            if(!groupName.equalsIgnoreCase("văn phòng")){
                laborProductivity=Float.valueOf(String.valueOf(child.stream().map(i ->
                        i.getLaborProductivity()).mapToInt(Integer::intValue).sum())) -
                        Float.valueOf(String.valueOf(child.stream().map(i ->
                                i.getPartTimeNum()).mapToInt(Integer::intValue).sum()));
            }
            Integer laborProductivity1=child.stream().map(i ->
                    i.getLaborProductivity()).mapToInt(Integer::intValue).sum();
            int partTimeNumber=child.stream().map(i ->i.getPartTimeNum()).mapToInt(Integer::intValue).sum();
            int studentNum=child.stream().map(i ->i.getStudentNum()).mapToInt(Integer::intValue).sum();
            int restNum=child.stream().map(i ->i.getRestNum()).mapToInt(Integer::intValue).sum();
            int riceCus=child.stream().map(i ->i.getRiceCus()).mapToInt(Integer::intValue).sum();
            int riceEmp=child.stream().map(i ->i.getRiceEmp()).mapToInt(Integer::intValue).sum();
            int riceVip=child.stream().map(i ->i.getRiceVip()).mapToInt(Integer::intValue).sum();
            Float ratio = (laborProductivity/totalLaborProductivity)*100;
            if(ratio.isNaN()==true){
                ratio=0.0f;
            }
            viewAllDtoList.removeIf(i -> i.getGroupId()==id.getId());
            viewAllDtoList.add(new ViewAllDto(groupID,groupParentId,groupName,reportDemarcation,laborProductivity1
                    ,partTimeNumber,studentNum,restNum,riceCus,riceEmp,riceVip,ratio,totalLaborProductivity,totalRatioOfOfficeAndDonvile));
        }
        Integer student = viewAllDtoList.stream().map(i ->i.getStudentNum()).mapToInt(Integer::intValue).sum();
        Integer partTimeToMay = viewAllDtoList.stream().filter(i->i.getGroupName().equalsIgnoreCase("Tổ may"))
                        .map(i->i.getPartTimeNum()).collect(Collectors.toList()).get(0);
        Integer partTimeDonViLe = viewAllDtoList.stream().filter(i->i.getGroupName().equalsIgnoreCase("Đơn vị lẻ"))
                .map(i->i.getPartTimeNum()).collect(Collectors.toList()).get(0);
        viewAllDtoList.add(new ViewAllDto(0, 0, "Học sinh chưa báo năng suất", student, 0
                , 0, 0, 0, 0, 0, 0, 0f, 0f, 0f));
        viewAllDtoList.add(new ViewAllDto(0, 0, "Thời vụ tổ may", partTimeToMay, partTimeToMay
                , 0, 0, 0, 0, 0, 0, 0f, 0f, 0f));
        viewAllDtoList.add(new ViewAllDto(0, 0, "Thời đơn vị lẻ ", partTimeDonViLe, partTimeDonViLe
                , 0, 0, 0, 0, 0, 0, 0f, 0f, 0f));
        return viewAllDtoList;
    }
    List<ViewDetailGroups> children( List<ViewDetailGroups> viewDetailsRes){
        Map<Integer, List<ViewDetailGroups>> parentIdToChildren = viewDetailsRes.stream()
                .collect(Collectors.groupingBy(ViewDetailGroups::getParentId));
        viewDetailsRes.forEach(p -> p.setChildren(parentIdToChildren.get(p.getKey())));
        return parentIdToChildren.get(0);
    }
}
