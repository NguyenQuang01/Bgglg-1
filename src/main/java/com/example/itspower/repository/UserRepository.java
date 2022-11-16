package com.example.itspower.repository;

import com.example.itspower.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long > {
    UserEntity findByUserName(String userName);
}
