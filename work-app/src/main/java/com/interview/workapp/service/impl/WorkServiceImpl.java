package com.interview.workapp.service.impl;

import com.interview.workapp.common.WorkStatus;
import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.entity.WorkEntity;
import com.interview.workapp.repository.WorkRepository;
import com.interview.workapp.service.WorkService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The type Work service.
 */
@Service
@Transactional
public class WorkServiceImpl implements WorkService {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    WorkRepository workRepository;

    @Override
    public ResponseEntity<WorkDto> save(WorkDto dto) {
        if (this.validation(dto)) {
            WorkEntity workEntity = modelMapper.map(dto, WorkEntity.class);
            workRepository.save(workEntity);
            return new ResponseEntity<WorkDto>(dto, HttpStatus.CREATED);
        }
        return new ResponseEntity<WorkDto>(dto, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<WorkDto> update(WorkDto dto) {
        if (dto != null && this.validation(dto)) {

            if (dto.getId() != null && dto.getId() != 0) {
                boolean findWork = workRepository.existsById(dto.getId());

                if (findWork) {
                    WorkEntity workEntity = modelMapper.map(dto, WorkEntity.class);
                    workRepository.save(workEntity);

                    return new ResponseEntity<WorkDto>(dto, HttpStatus.OK);
                } else {
                    return new ResponseEntity<WorkDto>(dto, HttpStatus.NOT_FOUND);
                }
            }
        }
        return new ResponseEntity<WorkDto>(dto, HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<WorkDto> delete(Integer id) {
        if (id != null && id != 0) {
            boolean findWork = workRepository.existsById(id);

            if (findWork) {
                workRepository.deleteById(id);

                return new ResponseEntity<WorkDto>(new WorkDto(), HttpStatus.OK);
            } else {
                return new ResponseEntity<WorkDto>(new WorkDto(), HttpStatus.NOT_FOUND);
            }
        }
        return new ResponseEntity<WorkDto>(new WorkDto(), HttpStatus.BAD_REQUEST);
    }

    private boolean validation(WorkDto dto) {
        if (dto.getWorkName() == null || dto.getWorkName().isEmpty()) {
            return false;
        } else if (dto.getEndingDate() == null) {
            return false;
        } else if (dto.getStartingDate() == null) {
            return false;
        } else if (dto.getStatus() == WorkStatus.COMPLETE.getText() || dto.getStatus() == WorkStatus.PLANNING.getText()
                ||dto.getStatus() == WorkStatus.DOING.getText()) {
            return false;
        } else if (dto.getStartingDate().isAfter(dto.getEndingDate())) {
            return false;
        }
        return true;
    }
}
