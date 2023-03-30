package com.example.itspower.model.entity;

import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.response.group.ViewDetailGroupResponse;
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



@SqlResultSetMapping(
        name = "viewDetailDto",
        classes = @ConstructorResult(
                targetClass = ViewDetailGroupResponse.class,
                columns = {
                        @ColumnResult(name = "groupKey", type = Integer.class),
                        @ColumnResult(name = "name", type = String.class),
                        @ColumnResult(name = "parentId", type = Integer.class),
                        @ColumnResult(name = "demarcation", type = Integer.class),
                        @ColumnResult(name = "laborProductivity", type = Integer.class),
                        @ColumnResult(name = "restEmp", type = Integer.class),
                        @ColumnResult(name = "partTimeEmp", type = Integer.class),
                        @ColumnResult(name = "studentNum", type = Integer.class),
                        @ColumnResult(name = "riceCus", type = Integer.class),
                        @ColumnResult(name = "riceVip", type = Integer.class),
                        @ColumnResult(name = "riceEmp", type = Integer.class),
                        @ColumnResult(name = "totalRiceNum", type = Integer.class),
                }
        )
)

@NamedNativeQuery(
        name = "findByViewDetail",
        query = "SELECT gr.id as groupKey ,gr.group_name as name, gr.parent_id as parentId , " +
                "r.demarcation as demarcation,  " +
                "r.labor_productivity as laborProductivity, r.rest_num as restEmp, " +
                "r.part_time_num as partTimeEmp, r.student_num as studentNum , " +
                "ri.rice_Cus as riceCus, ri.rice_vip as riceVip, ri.rice_emp as riceEmp, " +
                "(ri.rice_Cus + ri.rice_vip + ri.rice_emp) as totalRiceNum " +
                "FROM group_role gr left join report_system.report  r on r.group_id=gr.id left join rice ri on ri.report_id=r.id " +
                "Where DATE_FORMAT(r.report_date, '%Y%m%d') = DATE_FORMAT(:reportDate, '%Y%m%d') " ,
        resultSetMapping = "viewDetailDto"
)

@NamedNativeQuery(
        name = "findByViewDetailParent",
        query = "SELECT gr.id as groupKey ,gr.group_name as name, gr.parent_id as parentId , " +
                "r.demarcation as demarcation,  " +
                "r.labor_productivity as laborProductivity, r.rest_num as restEmp, " +
                "r.part_time_num as partTimeEmp, r.student_num as studentNum , " +
                "ri.rice_Cus as riceCus, ri.rice_vip as riceVip, ri.rice_emp as riceEmp, " +
                "(ri.rice_Cus + ri.rice_vip + ri.rice_emp) as totalRiceNum " +
                "FROM group_role gr left join report_system.report  r on r.group_id=gr.id left join rice ri on ri.report_id=r.id " +
                "where gr.parent_id is null " ,
        resultSetMapping = "viewDetailDto"
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
