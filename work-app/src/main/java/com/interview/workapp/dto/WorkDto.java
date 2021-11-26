package com.interview.workapp.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * The type Work dto.
 */
@Data
public class WorkDto {

    private Integer id;

    private String workName;

    private LocalDateTime startingDate;

    private LocalDateTime endingDate;

    private String status;
}
