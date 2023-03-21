package com.example.itspower.response;


import com.example.itspower.model.entity.GroupEntity;
import lombok.Data;

@Data
public class GroupRoleResponseChildren {
    private Integer id;
    private String groupName;
    private Integer parentId;
    public GroupRoleResponseChildren(GroupEntity groupEntity){
        this.id= groupEntity.getId();
        this.groupName= groupEntity.getGroupName();
        this.parentId= groupEntity.getParentId();
    }
}
