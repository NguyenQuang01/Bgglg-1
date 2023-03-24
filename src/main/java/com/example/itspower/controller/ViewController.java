package com.example.itspower.controller;

import com.example.itspower.repository.repositoryjpa.ReportJpaRepository;
import com.example.itspower.response.ViewDetailResponse;
import com.example.itspower.response.ViewRootResponse;
import com.example.itspower.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/view")
public class ViewController {
    @Autowired
    ViewService viewService;
    @GetMapping("/all")
    public ViewRootResponse getAll(){
        return viewService.getView("1211212");
    }
}
