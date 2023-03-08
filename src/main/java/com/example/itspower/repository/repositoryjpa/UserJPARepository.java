package com.example.itspower.repository.repositoryjpa;

import com.example.itspower.filter.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJPARepository extends JpaRepository<UserEntity,Long> {
    UserEntity findByUserName(String userName);
}
