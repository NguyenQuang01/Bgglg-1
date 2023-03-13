package com.example.itspower.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDetailResponse {
    private Integer id;
    private Double dinhBien;
    private Integer soNghi;
    private Integer thoiVu;
    private Integer hocSinh;
    private Integer soCom;
    private Number laoDongNangSuat;
    List<ListTransfer> transferList;
    List<ListRest> restList;
}
