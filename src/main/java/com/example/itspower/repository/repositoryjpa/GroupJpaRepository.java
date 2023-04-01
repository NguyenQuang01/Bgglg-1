package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.model.resultset.RootNameDto;
import com.example.itspower.response.group.ViewDetailGroupResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupJpaRepository extends JpaRepository<GroupEntity, Integer> {

    List<GroupEntity> findAllByParentId(int parentId);

    List<GroupEntity> findAllByParentIdIsNull();

    @Query(name = "findAllRoot", nativeQuery = true)
    List<RootNameDto> getAllRoot();

    @Query(value = "select distinct gr.parent_id from group_role gr where parent_id is not null ", nativeQuery = true)
    List<Integer> getAllParentId();

    @Query(name = "findByViewDetail", nativeQuery = true)
    List<ViewDetailGroupResponse> getDetail(@Param("reportDate") String reportDate);
    @Query(name = "findByViewDetailParent", nativeQuery = true)
    List<ViewDetailGroupResponse> getDetailParent();

    Optional<GroupEntity> findByGroupName(String groupName);

}
