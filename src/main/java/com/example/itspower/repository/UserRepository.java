package com.example.itspower.repository;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.repositoryjpa.UserJPARepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserRepository {
    private final UserJPARepository userJPARepository;

    public UserEntity save(UserEntity user) {
        return userJPARepository.save(user);
    }

    public UserEntity findByUser(String userLogin) {
        return userJPARepository.findByUserName(userLogin);
    }
}
