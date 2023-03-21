package com.example.itspower.response;

import com.example.itspower.model.entity.GroupEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GroupRoleResponse {
    private int id;
    private String groupName;
    private int parentId;
    List<GroupRoleResponseChildren> groupRoleResponseChildren;

    public GroupRoleResponse(GroupEntity groupEntity){
        this.id= groupEntity.getId();
        this.groupName= groupEntity.getGroupName();
        this.parentId= groupEntity.getParentId();
    }
}
