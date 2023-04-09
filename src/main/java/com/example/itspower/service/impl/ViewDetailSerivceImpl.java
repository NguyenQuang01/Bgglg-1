package com.example.itspower.service.impl;

import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.model.resultset.ViewAllDto;
import com.example.itspower.repository.GroupRoleRepository;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.group.ViewDetailsResponse;
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
    public List<ViewDetailsResponse> searchAllView() {
        List<RootNameDto> getIdRoot = groupJpaRepository.getAllRoot();
        List<ViewAllDto> viewAllDtoList = groupRoleRepository.searchAllView();
        Integer totalLaborProductivity = viewAllDtoList.stream().map(i ->i.getLaborProductivity())
                .mapToInt(Integer::intValue).sum();
        for(RootNameDto id :getIdRoot){
            List<ViewAllDto> child = viewAllDtoList.stream().filter(i -> i.getGroupParentId()==id.getId()
                            ||i.getGroupId()==id.getId()).collect(Collectors.toList());
            int reportDemarcation=child.stream().map(i ->i.getReportDemarcation()).mapToInt(Integer::intValue).sum();
            int laborProductivity=child.stream().map(i ->i.getLaborProductivity()).mapToInt(Integer::intValue).sum();
            int restNum=child.stream().map(i ->i.getRestNum()).mapToInt(Integer::intValue).sum();
            int riceVip=child.stream().map(i ->i.getRiceVip()).mapToInt(Integer::intValue).sum();
            int riceCus=child.stream().map(i ->i.getRiceCus()).mapToInt(Integer::intValue).sum();
            int riceEmp=child.stream().map(i ->i.getRiceEmp()).mapToInt(Integer::intValue).sum();
            float ratio = (laborProductivity/totalLaborProductivity)*100;
        }
        List<ViewDetailsResponse> viewDetailsRes = new ArrayList<>();
        viewAllDtoList.forEach(i -> {
            ViewDetailsResponse viewDetailsResponse = new ViewDetailsResponse(i);
            viewDetailsRes.add(viewDetailsResponse);
        });
        return children(viewDetailsRes);
    }
    List<ViewDetailsResponse> children( List<ViewDetailsResponse> viewDetailsRes){
        Map<Integer, List<ViewDetailsResponse>> parentIdToChildren = viewDetailsRes.stream()
                .collect(Collectors.groupingBy(ViewDetailsResponse::getGroupParentId));
        viewDetailsRes.forEach(p -> p.setChildren(parentIdToChildren.get(p.getGroupId())));
        return parentIdToChildren.get(0);
    }
}
