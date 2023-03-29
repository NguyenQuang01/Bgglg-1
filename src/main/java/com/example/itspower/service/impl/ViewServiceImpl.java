package com.example.itspower.service.impl;

import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.view.ViewDetailResponse;
import com.example.itspower.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewServiceImpl  implements ViewService {
    @Autowired
    ReportJpaRepository repository;
    @Autowired
    GroupJpaRepository groupJpaRepository;
    @Override
    public  List<ViewDetailResponse> getView(String date) {
        List<RootNameDto> getIdRoot = groupJpaRepository.getAllRoot();
        List<ViewDetailResponse> detailResponses = new ArrayList<>();
        for (RootNameDto idRoot : getIdRoot){
            ViewDetailResponse departmentReport= repository.viewRootReport(date,idRoot.getId());
            if(idRoot.getName().equalsIgnoreCase("Tổ may")==true){
                departmentReport.setTotalEmp(departmentReport.getTotalEmp()-departmentReport.getPartTimeEmp());
                departmentReport.setDepartment(idRoot.getName() + " (không tính thời vụ)");
            }else{
                departmentReport.setDepartment(idRoot.getName() );
            }
            detailResponses.add(departmentReport);
        }
       int student = detailResponses.stream().map(i->i.getStudent()).mapToInt(i -> i).sum();
        detailResponses.add(new ViewDetailResponse("Học sinh chưa báo năng suất",student,0));
        int sewingTeamSeason = detailResponses.stream().filter(i ->i.getDepartment().equalsIgnoreCase("Tổ may (không tính thời vụ)"))
                .map(i->i.getPartTimeEmp()).mapToInt(i -> i).sum();
        detailResponses.add(new ViewDetailResponse("Thời vụ tổ may",sewingTeamSeason,sewingTeamSeason));
        int oddUnitSeasonality = detailResponses.stream().filter(i ->i.getDepartment().equalsIgnoreCase("Đơn vị lẻ"))
                .map(i->i.getPartTimeEmp()).mapToInt(i -> i).sum();
        detailResponses.add(new ViewDetailResponse("Thời đơn vị lẻ",oddUnitSeasonality,oddUnitSeasonality));
        return detailResponses;
    }
}
