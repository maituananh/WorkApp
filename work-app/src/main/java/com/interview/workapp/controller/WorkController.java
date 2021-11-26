package com.interview.workapp.controller;

import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Work controller.
 */
@RestController
@RequestMapping("api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    /**
     * Save response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> save(@RequestBody WorkDto dto) {
        return workService.save(dto);
    }

    /**
     * Update response entity.
     *
     * @param dto the dto
     * @return the response entity
     */
    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> update(@RequestBody WorkDto dto) {
        return workService.update(dto);
    }

    /**
     * Delete response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<WorkDto> delete(@PathVariable Integer id) {
        return workService.delete(id);
    }

    /**
     * Delete response entity.
     *
     * @param sort the sort
     * @param page the page
     * @param size the size
     * @return the response entity
     */
    @GetMapping(path = "/fetch")
    public ResponseEntity<List<WorkDto>> delete(@RequestParam(defaultValue = "id") String sort,
                                                @RequestParam Integer page, @RequestParam Integer size) {
        return workService.fetchWithCondition(sort, page, size);
    }
}
