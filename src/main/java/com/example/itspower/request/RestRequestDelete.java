package com.example.itspower.request;

import lombok.Data;

import java.util.List;

@Data
public class RestRequestDelete {
    private Integer reportId;
    private List<Integer> restIds;
}
