package com.example.itspower.response;

import com.example.itspower.model.entity.ReportEntity;
import lombok.Data;

import java.util.Optional;

@Data
public class ReportRestNumResponse {
    private int restNum;
    private int userGroupId;
    private int id;

    public ReportRestNumResponse(Optional<ReportEntity> entity) {
//        this.restNum = entity.get().getRestNumber();
        this.id = entity.get().getId();
        this.userGroupId = entity.get().getUserGroupId();
    }
}
