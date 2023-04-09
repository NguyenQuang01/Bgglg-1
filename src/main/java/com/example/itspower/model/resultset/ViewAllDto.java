package com.example.itspower.model.resultset;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ViewAllDto {
    private Integer groupId;
    private Integer groupParentId;
    private String groupName;
    Integer reportDemarcation;
    private Integer laborProductivity;
    private Integer partTimeNum;
    private Integer restNum;
    private Integer studentNum;
    private Integer riceCus;
    private Integer riceEmp;
    private Integer riceVip;
    private Float ratio;
  private Float totalRatioOfOfficeAndDonvile;
  private Float totalLaborProductivity;

    public ViewAllDto(Integer groupId, Integer groupParentId, String groupName, Integer reportDemarcation, Integer laborProductivity, Integer partTimeNum, Integer restNum, Integer studentNum,
                      Integer riceCus, Integer riceEmp, Integer riceVip) {
        this.groupId = groupId;
        this.groupParentId = groupParentId;
        this.groupName = groupName;
        this.reportDemarcation = reportDemarcation==null ?0 :reportDemarcation;
        this.laborProductivity = laborProductivity ==null ?0: reportDemarcation;
        this.partTimeNum = partTimeNum ==null ?0:partTimeNum;
        this.restNum = restNum==null ?0:restNum;
        this.studentNum = studentNum==null ?0:studentNum;
        this.riceCus = riceCus==null ?0:riceCus;
        this.riceEmp = riceEmp==null ?0:riceEmp;
        this.riceVip = riceVip==null ?0:riceVip;

    }
    public ViewAllDto(Integer groupId, Integer groupParentId, String groupName,
                      Integer reportDemarcation, Float laborProductivity, Integer partTimeNum,
                      Integer restNum, Integer studentNum,
                      Integer riceCus, Integer riceEmp, Integer riceVip,Float ratio,Float totalLaborProductivity
            ,Float totalRatioOfOfficeAndDonvile) {
        this.groupId = groupId;
        this.groupParentId = groupParentId;
        this.groupName = groupName;
        this.reportDemarcation = reportDemarcation==null ?0 :reportDemarcation;
        this.laborProductivity = laborProductivity ==null ?0: reportDemarcation;
        this.partTimeNum = partTimeNum ==null ?0:partTimeNum;
        this.restNum = restNum==null ?0:restNum;
        this.studentNum = studentNum==null ?0:studentNum;
        this.riceCus = riceCus==null ?0:riceCus;
        this.riceEmp = riceEmp==null ?0:riceEmp;
        this.riceVip = riceVip==null ?0:riceVip;
        this.ratio   = ratio==null ?0:ratio;
        this.totalLaborProductivity=totalLaborProductivity;
        this.totalRatioOfOfficeAndDonvile=totalRatioOfOfficeAndDonvile;
    }
    public ViewAllDto(Integer groupId, Integer groupParentId, String groupName,
                      Integer reportDemarcation, Float laborProductivity, Integer partTimeNum,
                      Integer restNum, Integer studentNum,
                      Integer riceCus, Integer riceEmp, Integer riceVip,Float ratio) {
        this.groupId = groupId;
        this.groupParentId = groupParentId;
        this.groupName = groupName;
        this.reportDemarcation = reportDemarcation==null ?0 :reportDemarcation;
        this.laborProductivity = laborProductivity ==null ?0: reportDemarcation;
        this.partTimeNum = partTimeNum ==null ?0:partTimeNum;
        this.restNum = restNum==null ?0:restNum;
        this.studentNum = studentNum==null ?0:studentNum;
        this.riceCus = riceCus==null ?0:riceCus;
        this.riceEmp = riceEmp==null ?0:riceEmp;
        this.riceVip = riceVip==null ?0:riceVip;
        this.ratio   = ratio==null ?0:ratio;
    }
}
