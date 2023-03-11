package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface GroupRepository extends JpaRepository<GroupEntity,Integer> {
    @Query(value = "select * from group_name where id = parent_id",nativeQuery = true)
    List<GroupEntity> getRootId();
}
