package com.interview.workapp.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class WorkDto {

    private Integer id;

    private String workName;

    private LocalDate startingDate;

    private LocalDate endingDate;

    private String status;
}
