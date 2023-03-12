package com.example.itspower.service;

import com.example.itspower.response.RootResponse;
import com.example.itspower.response.RootResponseCompare;

import java.text.ParseException;

public interface ManagementService {
    RootResponse getListRoot(String date);
    RootResponseCompare getRoot(String date) throws ParseException;
}
