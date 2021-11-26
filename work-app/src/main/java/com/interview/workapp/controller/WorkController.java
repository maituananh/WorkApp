package com.interview.workapp.controller;

import com.interview.workapp.dto.WorkDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("api/work")
public class WorkController {

    @PostMapping(path = "/save", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> save(@RequestBody WorkDto dto){return null;}

    @PutMapping(path = "/update", consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<WorkDto> update(@RequestBody WorkDto t){return null;}

    @DeleteMapping(path = "/delete")
    public ResponseEntity<WorkDto> delete(Integer id){return null;}

    @DeleteMapping(path = "/delete-flush")
    public ResponseEntity<WorkDto> deleteAndFlush(Integer id){return null;}
}
