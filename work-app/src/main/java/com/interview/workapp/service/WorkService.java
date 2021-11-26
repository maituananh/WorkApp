package com.interview.workapp.service;

import com.interview.workapp.dto.WorkDto;
import org.springframework.http.ResponseEntity;

public interface WorkService {
    ResponseEntity <WorkDto> save(WorkDto dto);

    ResponseEntity <Object> update(WorkDto t);

    ResponseEntity <Object> delete(Integer id);

    ResponseEntity <Object> deleteAndFlush(Integer id);

    ResponseEntity <Object> findUser(WorkDto dto);

    ResponseEntity <Object> findAllUser();

    ResponseEntity <Object> find(Integer id);
}
