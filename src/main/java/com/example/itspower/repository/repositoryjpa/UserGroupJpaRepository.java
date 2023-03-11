package com.example.itspower.repository.repositoryjpa;
import com.example.itspower.model.entity.UserGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserGroupJpaRepository extends JpaRepository<UserGroupEntity, Integer> {
}
