package com.example.itspower.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface BaseSearchController<S> {
    @PostMapping("/page")
    ResponseEntity<Object> getPage(@Valid @RequestBody S s);
}
