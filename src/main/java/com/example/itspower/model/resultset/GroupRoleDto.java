package com.example.itspower.model.resultset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupRoleDto {
    private int id;
    private int parentId;
    private Integer demarcationAvailable;
    private String name;
    private String label;
}
