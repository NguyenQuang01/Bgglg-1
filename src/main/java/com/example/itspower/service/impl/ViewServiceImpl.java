package com.example.itspower.service.impl;

import com.example.itspower.repository.repositoryjpa.GroupJpaRepository;
import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.view.ViewDetailResponse;
import com.example.itspower.response.view.ViewRootResponse;
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
        date="2023-03-23";
        List<Integer> getIdRoot = groupJpaRepository.getAllRoot();
        ViewRootResponse response = new ViewRootResponse();
        List<ViewDetailResponse> detailResponses = new ArrayList<>();
        for (Integer idRoot : getIdRoot){
            detailResponses.add(repository.viewRootReport(date,idRoot));
        }
        response.setResponseList(detailResponses);

        return response;
    }
}
