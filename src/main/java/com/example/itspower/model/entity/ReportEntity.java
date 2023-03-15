package com.example.itspower.model.entity;

import com.example.itspower.model.resultset.ReportDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@Data
@NamedNativeQuery(
        name = "find_by_report",
        query ="select r.demarcation,r.rest_num as restNum,r.labor_productivity as laborProductivity, " +
                        "(select tr.transfer_num  from transfer tr where tr.report_id = r.id and tr.`type` = 0) as transferNum, " +
                        "(select tr1.transfer_num   from transfer tr1 where tr1.report_id = r.id and tr1.`type` = 1) as supportNum, " +
                        "r2.rest_name as restName,r4.name as seasonName, " +
                        "r.part_time_num  as partTimeNum, r.student_num  as studentNum, " +
                        "(r3.rice_cus +r3.rice_emp +r3.rice_vip) as totalRice from report r  " +
                        "inner join rest r2 on r2.report_id = r.id  " +
                        "inner join reason r4 on r4.id =r2.id  " +
                        "inner join rice r3 on r3.report_id = r.id  " +
                        "inner join transfer t on r.id =t.report_id  " +
                        "where r.report_date like ':reportDate%' " ,
        resultSetMapping = "report_dto",
        resultClass = ReportDto.class
)
@SqlResultSetMapping(
        name = "report_dto",
        classes = @ConstructorResult(
                targetClass = ReportDto.class,
                columns = {
                        @ColumnResult(name = "demarcation", type = Integer.class),
                        @ColumnResult(name = "restNum", type = Integer.class),
                        @ColumnResult(name = "laborProductivity", type = Integer.class),
                        @ColumnResult(name = "transferNum", type = Integer.class),
                        @ColumnResult(name = "supportNum", type = Integer.class),
                        @ColumnResult(name = "restName", type = String.class),
                        @ColumnResult(name = "seasonName", type = String.class),
                        @ColumnResult(name = "partTimeNum", type = Integer.class),
                        @ColumnResult(name = "studentNum", type = Integer.class),
                        @ColumnResult(name = "totalRice", type = Integer.class),
                }
        )
)
public class ReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "report_date")
    private Date name;
    @Column(name = "part_time_num")
    private Integer partTimeNum = 0;
    @Column(name = "student_num")
    private Integer studentNum = 0;
    @Column(name = "rest_num")
    private Integer restNum = 0;
    @Column(name = "group_id")
    private Integer groupId;
    @Column(name = "demarcation")
    private Integer demarcation = 0;
    @Column(name = "labor_productivity")
    private Integer laborProductivity = 0;
}
