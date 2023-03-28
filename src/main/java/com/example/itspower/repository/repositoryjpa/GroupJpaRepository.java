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
    @Query(value = "SELECT gr.id as groupId ,gr.parentId as parentId, ug.id  as userGroupId ,ug.userId  as  userId,r.id   as reportId,gr.groupName as groupName,r.demarcation as reportDemarcation," +
            "r.laborProductivity  as laborProductivity,   r.partTimeNum as partTimeNum, r.reportDate  as reportDate,r.restNum  as restNum, r.studentNum  as studentNum, gr.demarcationAvailable as demarcationAvailable \n" +
            "from GroupEntity as  gr left join UserGroupEntity as ug on gr.id = ug.id left join ReportEntity as r on ug.groupId = r.groupId")
    List<InterfaceReportDetails> findDetails();
}
