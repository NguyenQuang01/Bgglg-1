package com.example.itspower.response.group;

import com.example.itspower.model.resultset.GroupRoleDto;
import com.example.itspower.model.resultset.ViewAllDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GroupRoleResponse {

    private int value;
    private String label;
    private Integer parentId;

    List<GroupRoleResponse> children;

    public GroupRoleResponse(ViewAllDto response){
        this.value=response.getGroupId();
        this.label=response.getGroupName();
        this.parentId=response.getGroupParentId();
    }

    public GroupRoleResponse(GroupRoleDto groups){
        this.value=groups.getId();
        this.label=groups.getLabel();
        this.parentId=groups.getParentId();
    }
}
