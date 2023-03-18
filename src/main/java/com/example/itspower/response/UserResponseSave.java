package com.example.itspower.response;

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
    private UserEntity user;
    private GroupEntity group;
    private UserGroupEntity userGroup;
}
