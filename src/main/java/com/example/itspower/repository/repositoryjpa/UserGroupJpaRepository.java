package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface UserGroupJpaRepository extends JpaRepository<UserGroupEntity, Integer> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_group WHERE user_id =:userId", nativeQuery = true)
    void deleteGroupUser(@Param("userId") int userId);

    @Query(value = "SELECT * FROM user_group ug WHERE ug.user_id =:userId ", nativeQuery = true)
    Optional<UserGroupEntity> findByUserId(@Param("userId") int userId);
}
