package com.example.itspower.model.entity;

import com.example.itspower.model.resultset.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user")
@SqlResultSetMapping(
        name = "user_dto",
        classes = @ConstructorResult(
                targetClass = UserDto.class,
                columns = {
                        @ColumnResult(name = "groupName", type = String.class),
                        @ColumnResult(name = "isAdmin", type = Boolean.class),
                        @ColumnResult(name = "isEdit", type = Boolean.class),
                        @ColumnResult(name = "isReport", type = Boolean.class),
                        @ColumnResult(name = "isView", type = Boolean.class),
                        @ColumnResult(name = "groupId", type = Integer.class),

                }
        )
)

@NamedNativeQuery(
        name = "loginInfor",
        query = "select u.is_admin as isAdmin ,IFNULL(gr.group_name,'') as groupName, " +
                "u.is_edit as isEdit,u.is_report as isReport, " +
                "u.is_view as isView, IFNULL(ug.group_id,0) as groupId " +
                "from `user` u " +
                "left join user_group ug on ug.user_id = u.id  " +
                "left join group_role gr on ug.group_id = gr .id where u.user_login = :userLogin ",
        resultSetMapping = "user_dto"
)
public class UserEntity {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_login")
    private String userLogin;
    @Column(name = "password")
    private String password;
    @Column(name = "is_admin")
    private boolean isAdmin;
    @Column(name = "is_report")
    private boolean isReport;
    @Column(name = "is_edit")
    private boolean isEdit;
    @Column(name = "is_view")
    private boolean isView;
}
