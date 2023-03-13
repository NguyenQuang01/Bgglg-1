package com.example.itspower.service.impl;

import com.example.itspower.model.entity.UserEntity;
import com.example.itspower.repository.UserRepository;
import com.example.itspower.response.InforUser;
import com.example.itspower.response.search.AddToUserForm;
import com.example.itspower.service.UserService;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    private final EntityManager entityManager;

    public UserServiceImpl(@Qualifier("primaryEntityManager") EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public UserEntity save(AddToUserForm userForm) {
        UserEntity user = new UserEntity();
        user.setUserLogin(userForm.getUserLogin());
        user.setUserName(userForm.getUserName());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public InforUser getInfoUser(String loginName) {
        try{
            StringBuilder query = new StringBuilder();
            query.append("select user_login as userName ,read_report as readReport ,edit_report as updateReport,\n" +
                    " creat_report as createReport, group_id as groupId, gn.name as groupName\n" +
                    " from user u inner join user_Group ug  on u.id =ug.user_id inner join group_name gn \n" +
                    " on ug.group_id =gn.id where u.user_name ='"+loginName+"'");
            Query queryResult = entityManager.createNativeQuery(query.toString());
            return (InforUser) queryResult.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(InforUser.class)).getSingleResult();
        }catch (Exception e){
            return new InforUser();
        }
    }
}
