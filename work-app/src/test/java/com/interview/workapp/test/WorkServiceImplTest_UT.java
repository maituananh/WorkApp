package com.interview.workapp.test;

import com.interview.workapp.common.WorkStatus;
import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.entity.WorkEntity;
import com.interview.workapp.repository.WorkRepository;
import com.interview.workapp.service.WorkService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doAnswer;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WorkServiceImplTest_UT {

    @MockBean
    private WorkRepository workRepository;

    private WorkDto workDto;

    private WorkEntity workEntity;

    @Autowired
    private WorkService workService;

    @Autowired
    private ModelMapper modelMapper;

    private String sort = "id";
    private final Integer page = 0;
    private final Integer size = 4;

    @Before
    public void setUp() {
        LocalDateTime now = LocalDateTime.now();
        workDto = new WorkDto();
        workDto.setId(1);
        workDto.setWorkName("Task-test");
        workDto.setStatus("DOING");
        workDto.setEndingDate(now);
        workDto.setStartingDate(now);

        workEntity = modelMapper.map(workDto, WorkEntity.class);
    }
    //    =========== START SAVE API ==========
    @Test
    public void createWork_WhenWorkDtoIsCorrect_ReturnCreated() {
        Mockito.when(workRepository.save(workEntity)).thenReturn(workEntity);
        ResponseEntity<WorkDto> response = workService.save(workDto);
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCodeValue());
        Assert.assertEquals(workDto, response.getBody());
    }

    @Test
    public void createWork_WhenWorkNameNull_ReturnBadRequest() {
        workDto.setWorkName(null);
        ResponseEntity<WorkDto> response = workService.save(workDto);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Assert.assertEquals(workDto, response.getBody());
    }
    //    =========== END SAVE API ==========

    //    =========== START UPDATE API ==========
    @Test
    public void updateWork_WhenWorkDtoIsCorrect_ReturnOK() {
        Mockito.when(workRepository.existsById(1)).thenReturn(true);
        Mockito.when(workRepository.save(workEntity)).thenReturn(workEntity);
        ResponseEntity<WorkDto> response = workService.update(workDto);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(workDto, response.getBody());
    }

    @Test
    public void updateWork_WhenWorkNameNull_ReturnBadRequest() {
        workDto.setId(null);
        ResponseEntity<WorkDto> response = workService.update(workDto);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Assert.assertEquals(workDto, response.getBody());
    }
    //    =========== END UPDATE API ==========

    //    =========== START DELETE API ==========
    @Test
    public void deleteWork_WhenWorkIdIsCorrect_ReturnOK() {
        Mockito.when(workRepository.existsById(1)).thenReturn(true);
        doAnswer(invocation -> null).when(workRepository).deleteById(1);
        ResponseEntity<WorkDto> response = workService.delete(1);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(), new WorkDto());
    }

    @Test
    public void deleteWork_WhenWorkIdNotFound_ReturnNotFound() {
        Mockito.when(workRepository.existsById(1)).thenReturn(false);
        ResponseEntity<WorkDto> response = workService.delete(1);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(), new WorkDto());
    }
    //    =========== END DELETE API ==========

    //    =========== START FETCH API ==========
    @Test
    public void fetchWork_WhenIsCorrect_ReturnOK() {
        List<WorkEntity> workEntities = dataTest();
        Page<WorkEntity> pagedResult = new PageImpl<WorkEntity>(workEntities);

        Mockito.when(workRepository.findAll(PageRequest.of(page, size, Sort.by(sort))))
                .thenReturn(pagedResult);

        ResponseEntity<List<WorkDto>> response = workService.fetchWithCondition(sort, page, size);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(), workEntities.stream().map(entity->modelMapper.map(entity, WorkDto.class))
                .collect(Collectors.toList()));
    }

    @Test
    public void fetchWork_WhenSortNameIsNone_ReturnBadRequest() {
        this.sort = "None";
        Mockito.when(workRepository.existsById(1)).thenReturn(false);
        ResponseEntity<List<WorkDto>> response = workService.fetchWithCondition(sort, page, size);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(), new ArrayList<>());
    }

    @Test
    public void fetchWork_WhenNotFoundRecord_ReturnNotFound() {
        Page<WorkEntity> pagedResult = new PageImpl<WorkEntity>(new ArrayList<>(0));
        Mockito.when(workRepository.findAll(PageRequest.of(page, size, Sort.by(sort))))
                .thenReturn(pagedResult);

        ResponseEntity<List<WorkDto>> response = workService.fetchWithCondition(sort, page, size);

        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
        Assert.assertEquals(response.getBody(), new ArrayList<>());
    }
    //    =========== END FETCH API ==========

    private List<WorkEntity> dataTest() {
        List<WorkEntity> workEntities = new ArrayList<>(4);
        for (int i = 0; i < 4; i++) {
            WorkEntity workEntity = new WorkEntity();
            workEntity.setId(i + 1);
            workEntity.setWorkName("data test - " + (i + 1));
            workEntity.setStatus(WorkStatus.COMPLETE.getText());
            workEntities.add(workEntity);
        }
       return workEntities;
    }
}
