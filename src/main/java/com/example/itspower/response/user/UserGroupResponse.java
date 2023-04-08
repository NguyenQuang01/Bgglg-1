package com.example.itspower.response.user;

import com.example.itspower.model.entity.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserGroupResponse {
    private Integer userId = 0;
    private Integer groupId = 0;

    public UserGroupResponse(UserGroupEntity entity) {
        this.userId = entity.getUserId();
        this.groupId = entity.getGroupId();
    }
}
