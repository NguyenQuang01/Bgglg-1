package com.example.itspower.response;

import com.example.itspower.component.util.DateUtils;
import com.example.itspower.model.entity.ReportDtlEntity;
import com.example.itspower.model.entity.ReportEntity;
import lombok.Data;

@Data
public class ReportResponse {
    private String createBy;
    private int userGroupId;
    private String orderDate;
    public ReportDtlResponse responses;

    public ReportResponse(ReportEntity reportEntity, ReportDtlEntity dtlEntities) {
        this.orderDate = DateUtils.formatDate(reportEntity.getOrderDate());
        this.createBy = reportEntity.getCreateBy();
        this.userGroupId = reportEntity.getUserGroupId();
        this.responses = new ReportDtlResponse(dtlEntities);
    }
}
