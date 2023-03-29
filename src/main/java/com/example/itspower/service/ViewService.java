package com.example.itspower.service;

import com.example.itspower.response.view.ViewDetailResponse;

import java.util.List;

public interface ViewService {
   List<ViewDetailResponse> getView(String date);
}
