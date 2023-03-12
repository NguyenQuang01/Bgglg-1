package com.example.itspower.repository.repositoryjpa;
import com.example.itspower.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserJPARepository extends JpaRepository<UserEntity, Integer> {
    UserEntity findByUserName(String userName);
}