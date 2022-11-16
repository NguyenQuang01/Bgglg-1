package com.example.itspower.repository;

import com.example.itspower.entity.RolesEntity;
import com.example.itspower.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<RolesEntity,Long > {
    RolesEntity findByName(String Name);
}
