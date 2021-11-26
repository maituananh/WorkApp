package com.interview.workapp.service;

import com.interview.workapp.dto.WorkDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * The interface Work service.
 */
public interface WorkService {
    /**
     * Save response entity .
     *
     * @param dto the dto
     * @return the response entity
     */
    ResponseEntity <WorkDto> save(WorkDto dto);

    /**
     * Update response entity .
     *
     * @param t the t
     * @return the response entity
     */
    ResponseEntity <WorkDto> update(WorkDto t);

    /**
     * Delete response entity .
     *
     * @param id the id
     * @return the response entity
     */
    ResponseEntity <WorkDto> delete(Integer id);

    /**
     * Fetch with condition response entity .
     *
     * @param sort the sort
     * @param page the page
     * @param size the size
     * @return the response entity
     */
    ResponseEntity <List<WorkDto>> fetchWithCondition(String sort, Integer page, Integer size);
}
