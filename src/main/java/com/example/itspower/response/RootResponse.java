package com.example.itspower.response;

import lombok.Data;

import java.util.List;

@Data
public class RootResponse {
    //tong lao dong nang suat
    private Integer TotalLaborReporting;
    //tong nguoi lao dong
    private Integer TotalActualWorking;
    //tong ty le
    private Float TotalTheValue;
    List<ListRootChildResponse> child;

}
