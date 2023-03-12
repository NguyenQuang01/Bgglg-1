package com.example.itspower.response;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReportResponse {
    private int restNum;
    private int userGroupId;
    private String orderDate;
    public List<ReportDtlResponse> responses = new ArrayList<>();

    public ReportResponse(ReportEntity reportEntity, List<ReportDtlEntity> reportDtlEntities) {
//        this.restNum = reportEntity.getRestNumber();
        this.orderDate = DateUtils.formatDate(reportEntity.getOrderDate());
        this.userGroupId = reportEntity.getUserGroupId();
        reportDtlEntities.forEach(i -> this.responses.add(new ReportDtlResponse(i)));
    }
}
