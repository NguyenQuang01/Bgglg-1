package com.example.itspower.response.user;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.entity.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseSave {
    private String userName;
    private boolean isEdit;
    private boolean isReport;
    private boolean isView;
    private boolean isAdmin;
    private GroupResponse group;
    private UserGroupResponse userGroup;

    public UserResponseSave(UserEntity user, Optional<GroupEntity> groupEntity, UserGroupEntity userGroup) {
        this.userName = user.getUserLogin();
        this.isEdit = user.isEdit();
        this.isReport = user.isReport();
        this.isView = user.isView();
        this.isAdmin = user.isAdmin();
        if (groupEntity.isPresent()) {
            this.group = new GroupResponse(groupEntity.get());
            this.userGroup = new UserGroupResponse(userGroup);
        }

    }
}
