package com.example.itspower.response.view;

import com.example.itspower.response.view.ViewDetailResponse;
import lombok.Data;

import java.util.List;

@Data
public class ViewRootResponse {
    // tong lam viec thuc te
    private Integer actualWork;
    //tổng lao động năng xuất
    private Integer laborProductivity;
    // TỔNG PHẦN TRĂM
    private Double totalratio;
    List<ViewDetailResponse> responseList;
}
