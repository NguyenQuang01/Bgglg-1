package com.example.itspower.service.impl;

import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.ViewDetailResponse;
import com.example.itspower.response.ViewRootResponse;
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
    public ViewRootResponse getView(String date) {
        List<RootNameDto> getIdRoot = groupJpaRepository.getAllRoot();
        ViewRootResponse response = new ViewRootResponse();
        List<ViewDetailResponse> detailResponses = new ArrayList<>();
        int totalWorkActualWork =0;
        int laborProductivity =0;
        int riceCus =0;
        int riceEmp =0;
        int riceVip =0;
        double totalRatio = 0.0;
        for (RootNameDto idRoot : getIdRoot){
            ViewDetailResponse departmentReport= repository.viewRootReport(date,idRoot.getId());
            departmentReport.setDepartment(idRoot.getName());
            detailResponses.add(departmentReport);
        }
        response.setResponseList(detailResponses);
        for (int i = 0 ; i < detailResponses.size();i++){
            totalWorkActualWork +=detailResponses.get(i).getTotalEmp();
            laborProductivity += detailResponses.get(i).getLaborProductivityTeam();
            totalRatio += detailResponses.get(i).getRatio();
            riceEmp += detailResponses.get(i).getRiceEmp();
            riceVip += detailResponses.get(i).getRiceVip();
            riceCus += detailResponses.get(i).getRiceCus();
        }
        response.setTotalRiceEmp(riceEmp);
        response.setTotalRiceCus(riceCus);
        response.setTotalRiceVip(riceVip);
        response.setActualWork(totalWorkActualWork);
        response.setLaborProductivity(laborProductivity);
        response.setTotalratio((double) Math.round(totalRatio/getIdRoot.size()));
        return response;
    }
}
