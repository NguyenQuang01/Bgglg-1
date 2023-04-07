package com.example.itspower.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GroupRoleRequest {

    private String groupName;
    private Integer demarcation;
    private Integer parentId;
}
