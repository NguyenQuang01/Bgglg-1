package com.example.itspower.controller;

import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.ViewDetailResponse;
import com.example.itspower.response.ViewRootResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("/view")
public class ViewController {
    @Autowired
    ReportJpaRepository repository;
    @GetMapping("/all")
    public List<ViewRootResponse> getAll(){
        ViewDetailResponse responses=repository.viewRootReport("2023-03-23",4);
        return null;
    }
}
