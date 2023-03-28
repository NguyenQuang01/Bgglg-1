package com.example.itspower.model.entity;

import com.example.itspower.model.resultset.RootNameDto;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "group_role")
@Data
@SqlResultSetMapping(
        name = "RootNameDto",
        classes = @ConstructorResult(
                targetClass = RootNameDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                }
        )
)

@NamedNativeQuery(
        name = "findAllRoot",
        query = "select id,group_name as name from group_role where parent_id is null",
        resultSetMapping = "RootNameDto"
)

@NamedNativeQuery(
        name = "findDetails",
        query = "select gr.id                as groupId,\n" +
                "       gr.group_name        as groupName,\n" +
                "       gr.parent_id         as parentId,\n" +
                "       gr.demarcation_available         as demarcationAvailable,\n" +
                "       ug.id                as userGroupId,\n" +
                "       ug.user_id           as userId,\n" +
                "       r.id                 as reportId,\n" +
                "       r.demarcation        as reportDemarcation,\n" +
                "       r.labor_productivity as laborProductivity,\n" +
                "       r.part_time_num      as partTimeNum,\n" +
                "       r.report_date        as reportDate,\n" +
                "       r.rest_num           as restNum,\n" +
                "       r.student_num        as studentNum\n" +
                "from group_role gr\n" +
                "         left join user_group ug on gr.id = ug.id\n" +
                "         left join report r on ug.group_id = r.group_id",
        resultSetMapping = "GroupRoleAndReportDetailsRes"
)
@SqlResultSetMapping(
        name = "GroupRoleAndReportDetailsRes",
        entities = {
                @EntityResult(
                        entityClass = GroupEntity.class,
                        fields = {
                                @FieldResult(name = "id", column = "groupId"),
                                @FieldResult(name = "groupName", column = "groupName"),
                                @FieldResult(name = "parentId", column = "parentId"),
                                @FieldResult(name = "demarcationAvailable", column = "demarcationAvailable")
                        }
                ),
                @EntityResult(
                        entityClass = ReportEntity.class,
                        fields = {
                                @FieldResult(name = "id", column = "reportId"),
                                @FieldResult(name = "reportDate", column = "reportDate"),
                                @FieldResult(name = "partTimeNum", column = "partTimeNum"),
                                @FieldResult(name = "studentNum", column = "studentNum"),
                                @FieldResult(name = "restNum", column = "restNum"),
                                @FieldResult(name = "groupId", column = "groupId"),
                                @FieldResult(name = "demarcation", column = "reportDemarcation"),
                                @FieldResult(name = "laborProductivity", column = "laborProductivity")
                        }
                ),
                @EntityResult(
                        entityClass = UserGroupEntity.class,
                        fields = {
                                @FieldResult(name = "id", column = "userGroupId"),
                                @FieldResult(name = "userId", column = "userId"),
                                @FieldResult(name = "groupId", column = "groupId")
                        }
                )
        }
)

public class GroupEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "group_name")
    private String groupName = "";
    @Column(name = "parent_id")
    private Integer parentId;
    @Column(name = "demarcation_available")
    private Integer demarcationAvailable;


}
