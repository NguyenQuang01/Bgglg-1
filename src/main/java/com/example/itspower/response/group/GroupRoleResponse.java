package com.example.itspower.response.group;

import com.example.itspower.model.resultset.GroupRoleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GroupRoleResponse {

    private int id;
    private String value;
    private String label;
    private Integer parentId;

    List<GroupRoleResponse> children;

    public GroupRoleResponse(GroupRoleDto groups){
        this.id=groups.getId();
        this.value=groups.getName();
        this.label=groups.getLabel();
        this.parentId=groups.getParentId();
    }
}
