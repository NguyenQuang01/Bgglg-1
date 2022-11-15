package com.example.itspower.service;

import com.example.itspower.dto.search.SearchDto;
import org.springframework.data.domain.Page;

public interface BaseSearchService<T, S extends SearchDto> {

    Page<T> page(S s);
}
