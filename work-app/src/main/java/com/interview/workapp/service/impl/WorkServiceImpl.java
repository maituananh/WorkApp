package com.interview.workapp.service.impl;

import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.service.WorkService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Work service.
 */
@Service
@Transactional
public class WorkServiceImpl implements WorkService {

    @Override
    public ResponseEntity<Object> save(WorkDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> update(WorkDto t) {
        return null;
    }

    @Override
    public ResponseEntity<Object> delete(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> deleteAndFlush(Integer id) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findUser(WorkDto dto) {
        return null;
    }

    @Override
    public ResponseEntity<Object> findAllUser() {
        return null;
    }

    @Override
    public ResponseEntity<Object> find(Integer id) {
        return null;
    }
}
