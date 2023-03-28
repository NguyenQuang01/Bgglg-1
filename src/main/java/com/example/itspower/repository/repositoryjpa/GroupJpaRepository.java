package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.response.group.InterfaceReportDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupJpaRepository extends JpaRepository<GroupEntity, Integer> {

    List<GroupEntity> findAllByParentId(int parentId);

    List<GroupEntity> findAllByParentIdIsNull();

    @Query(name = "findAllRoot", nativeQuery = true)
    List<RootNameDto> getAllRoot();

    @Query(value = "SELECT gr.id as groupId,gr.group_name as groupName, gr.parent_id as parentId , " +
            "r.demarcation as totalEmpDemarcation,  " +
            "r.labor_productivity as laborProductivity, r.rest_num as restEmp, " +
            "r.part_time_num as partTimeEmp, r.student_num as studentNum , " +
            "ri.rice_Cus as riceCus, ri.rice_vip as riceVip, ri.rice_emp as riceEmp " +
            "FROM group_role gr left join report_system.report  r on r.group_id=gr.id left join rice ri on ri.report_id=r.id ", nativeQuery = true)
    List<InterfaceReportDetails> findDetails();
}
