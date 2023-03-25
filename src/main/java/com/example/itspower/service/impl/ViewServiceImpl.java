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
import java.util.stream.Collectors;

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
        List<Integer> totalStudent = detailResponses.stream().map(i -> i.getStudent()).collect(Collectors.toList());
        int studentDoNotReportProductivity = totalStudent.stream().mapToInt(Integer::intValue).sum();

        for (int i = 0 ; i < detailResponses.size();i++){
            if(detailResponses.get(i).getTotalEmp() !=null){
                totalWorkActualWork +=detailResponses.get(i).getTotalEmp();
            }
            if(detailResponses.get(i).getLaborProductivityTeam() !=null){
                laborProductivity += detailResponses.get(i).getLaborProductivityTeam();
            }
            if( detailResponses.get(i).getRatio() !=null){
                totalRatio += detailResponses.get(i).getRatio();
            }
            if( detailResponses.get(i).getRiceEmp() !=null){
                riceEmp += detailResponses.get(i).getRiceEmp();
            }
            if( detailResponses.get(i).getRiceVip() !=null){
                riceVip += detailResponses.get(i).getRiceVip();
            }
            if(detailResponses.get(i).getRiceCus() !=null){
                riceCus += detailResponses.get(i).getRiceCus();
            }
        }
        Double ratioStudent =Double.valueOf(studentDoNotReportProductivity/totalWorkActualWork)*100;
        detailResponses.add(new ViewDetailResponse("Học sinh chưa báo năng suất",
                studentDoNotReportProductivity,ratioStudent));
        detailResponses.add( new ViewDetailResponse("Thời vụ tổ may",0,0.0));
        detailResponses.add( new ViewDetailResponse("Thời vụ đơn vị lẻ",0,0.0));
        response.setResponseList(detailResponses);
        response.setTotalRiceEmp(riceEmp);
        response.setTotalRiceCus(riceCus);
        response.setTotalRiceVip(riceVip);
        response.setActualWork(totalWorkActualWork);
        response.setLaborProductivity(laborProductivity);
        response.setTotalratio((double) Math.round(totalRatio/getIdRoot.size()));
        return response;
    }
}
