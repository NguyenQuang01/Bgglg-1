package com.example.itspower.service.impl;
import com.example.itspower.model.entity.GroupEntity;
import com.example.itspower.repository.repositoryjpa.GroupRepository;
import com.example.itspower.response.RootResponse;
import com.example.itspower.service.ManagementService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ManagementServiceImpl implements ManagementService {
    private final GroupRepository groupRepository;

    public ManagementServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<RootResponse> getListRoot() {
        List<GroupEntity> root=groupRepository.getRootId();
        return null;
    }
}
