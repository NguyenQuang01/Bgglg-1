package com.example.itspower.service;

import com.example.itspower.response.group.ViewDetailGroups;

import java.util.List;

public interface ViewDetailService {
    List<ViewDetailGroups> searchAllView(String reportDate);
}
