package com.interview.workapp.controller;

import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/work")
public class WorkController {

    @Autowired
    private WorkService workService;

    @PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> save(@RequestBody WorkDto dto){return workService.save(dto);}

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> update(@RequestBody WorkDto dto){return workService.update(dto);}

    @DeleteMapping(path = "/delete")
    public ResponseEntity<WorkDto> delete(Integer id){return null;}

    @DeleteMapping(path = "/delete-flush")
    public ResponseEntity<WorkDto> deleteAndFlush(Integer id){return null;}
}
