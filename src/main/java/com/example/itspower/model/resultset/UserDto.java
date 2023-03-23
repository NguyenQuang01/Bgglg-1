package com.example.itspower.model.resultset;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private int userId;
    private String groupName;
    private boolean isAdmin;
    private boolean isEdit;
    private boolean isReport;
    private boolean isView;
    private int groupId;
}
