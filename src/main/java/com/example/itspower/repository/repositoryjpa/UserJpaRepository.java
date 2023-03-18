package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.model.resultset.UserDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserJpaRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserEntity> {
    Optional<UserEntity> findByUserLogin(String userName);

    @Query(name = "loginInfor", nativeQuery = true)
    UserDto loginInfor(@Param("userLogin") String userLogin);
}