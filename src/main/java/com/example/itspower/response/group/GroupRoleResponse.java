package com.example.itspower.response.group;

import com.example.itspower.model.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class GroupRoleResponse {
    private Integer id;
    private String groupName;
    private Integer parentId;
    List<GroupRoleResponse> groups;

    public GroupRoleResponse(GroupEntity groups){
        this.id=groups.getId();
        this.groupName=groups.getGroupName();
        this.parentId=groups.getParentId();
    }
}
