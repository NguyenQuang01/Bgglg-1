package com.example.itspower.model.entity;

import com.example.itspower.model.resultset.ReportDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "report")
@Data
@SqlResultSetMapping(
        name = "report_dto",
        classes = @ConstructorResult(
                targetClass = ReportDto.class,
                columns = {
                        @ColumnResult(name = "id", type = Integer.class),
                        @ColumnResult(name = "demarcation", type = Integer.class),
                        @ColumnResult(name = "laborProductivity", type = Integer.class),
                        @ColumnResult(name = "transferNum", type = Integer.class),
                        @ColumnResult(name = "supportNum", type = Integer.class),
                        @ColumnResult(name = "restNum", type = Integer.class),
                        @ColumnResult(name = "partTimeNum", type = Integer.class),
                        @ColumnResult(name = "studentNum", type = Integer.class),
                        @ColumnResult(name = "totalRice", type = Integer.class)
                }
        )
)
@NamedNativeQuery(
        name = "find_by_report",
        query = " select r.id,r.demarcation,r.labor_productivity as laborProductivity, " +
                "(select tr.transfer_num  from transfer tr where tr.report_id = r.id and tr.`type` = 0) as transferNum,  " +
                "(select tr1.transfer_num   from transfer tr1 where tr1.report_id = r.id and tr1.`type` = 1) as supportNum, " +
                "(select count(re.report_id)  from rest re " +
                "inner join report rp on re.report_id = rp.id " +
                "inner join reason rs on rs.id =re.reason_id  where re.report_id = r.id) as restNum, " +
                "r.part_time_num  as partTimeNum, r.student_num  as studentNum, " +
                "(r3.rice_cus +r3.rice_emp +r3.rice_vip) as totalRice from report r  " +
                "inner join rest r2 on r2.report_id = r.id " +
                "inner join reason r4 on r4.id =r2.id  inner join rice r3 on r3.report_id = r.id  " +
                "where DATE_FORMAT(r.report_date, '%Y%m%d') = DATE_FORMAT(:reportDate, '%Y%m%d') ",
        resultSetMapping = "report_dto"
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
