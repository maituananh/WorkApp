package com.interview.workapp.service;

import com.interview.workapp.dto.WorkDto;
import org.springframework.http.ResponseEntity;

public interface WorkService {
    ResponseEntity <WorkDto> save(WorkDto dto);

    ResponseEntity <WorkDto> update(WorkDto t);

    ResponseEntity <WorkDto> delete(Integer id);

}
