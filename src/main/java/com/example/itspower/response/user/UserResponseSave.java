package com.example.itspower.response.user;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.entity.UserGroupEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseSave {
    private String userName;
    private boolean isEdit;
    private boolean isReport;
    private boolean isView;
    private boolean isAdmin;
    private GroupEntity group;
    private UserGroupEntity userGroup;

    public UserResponseSave(UserEntity user, GroupEntity group, UserGroupEntity userGroup) {
        this.userName = user.getUserLogin();
        this.isEdit = user.isEdit();
        this.isReport = user.isReport();
        this.isView = user.isView();
        this.isAdmin = user.isAdmin();
        this.group = group;
        this.userGroup = userGroup;
    }
}
