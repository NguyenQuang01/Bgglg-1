package com.example.itspower.response;

import lombok.Data;

import java.util.List;

@Data
public class ViewRootResponse {
    // tong lam viec thuc te
    private Integer actualWork;
    //tổng lao động năng xuất
    private Integer laborProductivity;
    private Integer totalRiceCus;
    private Integer totalRiceEmp;
    private Integer totalRiceVip;
    // TỔNG PHẦN TRĂM
    private Double totalratio;
    List<ViewDetailResponse> responseList;
}
