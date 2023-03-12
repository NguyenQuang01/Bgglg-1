package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Integer> {
    @Query(value = "select * from group_name  where id = parent_id ",nativeQuery = true)
    List<GroupEntity> getRootId();
    @Query(value = "select IFNULL(sum(emp_num),0) as total from reportdtl dtl inner join report r on r.id=dtl.report_id" +
                "  where groupid in(select id from group_name where parent_id = ?1 ) and r.create_at = ?2",nativeQuery = true)
    Integer totalEmployee(Integer parentId,String date);

    @Query(value = "select (emp_num+part_time_num+student_num-rest_number-student_num-num_Support-num_transfer)" +
            " as totalEmpProductivity" +
            " from reportdtl dtl" +
            " inner join report r on r.id=dtl.report_id " +
            "where groupid in (select id from group_name where parent_id = ?1) and r.create_at = ?2 ",
                nativeQuery = true)
    Integer totalEmpProductivity(Integer parentId,String date);

}
