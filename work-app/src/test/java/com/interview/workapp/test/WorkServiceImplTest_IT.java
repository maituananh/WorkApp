package com.interview.workapp.test;

import com.interview.workapp.dto.WorkDto;
import com.interview.workapp.entity.WorkEntity;
import com.interview.workapp.repository.WorkRepository;
import com.interview.workapp.service.WorkService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The type Work service impl test it.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WorkServiceImplTest_IT {

    @Autowired
    private WorkRepository workRepositoryReal;

    private WorkDto workDto;

    /**
     * The Work service.
     */
    @Autowired
    WorkService workService;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    /**
     * The Model mapper.
     */
    @Autowired
    ModelMapper modelMapper;

    private String sortParam = "id";
    private Integer pageParam = 0;
    private Integer sizeParam = 4;

    private static final String BASE_URL = "http://localhost:";
    private static final String PREFIX_URL = "/api/work";
    private static String FULL_URL = null;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        FULL_URL = BASE_URL + port + PREFIX_URL;

        LocalDateTime now = LocalDateTime.now();
        workDto = new WorkDto();
        workDto.setWorkName("Task-test");
        workDto.setStatus("DOING");
        workDto.setEndingDate(now);
        workDto.setStartingDate(now);
    }

    /**
     * Save work when work is correct return created.
     */
//    =========== START SAVE API ==========
    @Test
    public void saveWork_WhenWorkIsCorrect_ReturnCreated()
    {
        workDto.setWorkName("Test-rename-workName");
        ResponseEntity<WorkDto> response = this.restTemplate.postForEntity(FULL_URL + "/save", workDto, WorkDto.class);
        Assert.assertEquals(HttpStatus.CREATED.value(), response.getStatusCode().value());
        Assert.assertEquals(workDto, response.getBody());
    }

    /**
     * Save work when work status is none return bad request.
     */
    @Test
    public void saveWork_WhenWorkStatusIsNone_ReturnBadRequest()
    {
        workDto.setStatus("None");
        ResponseEntity<WorkDto> response = this.restTemplate.postForEntity(FULL_URL + "/save", workDto, WorkDto.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        Assert.assertEquals(workDto, response.getBody());
    }
    //    =========== END SAVE API ==========

    /**
     * Fetch work when requests param is correct return ok.
     */
//    =========== START FETCH API ==========
    @Test
    public void fetchWork_WhenRequestsParamIsCorrect_ReturnOK()
    {
        String url = String.format(FULL_URL + "/fetch?sort=%s&page=%d&size=%d", sortParam, pageParam, sizeParam);
        ResponseEntity<List> response = this.restTemplate.getForEntity(url, List.class);
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCode().value());
        Assert.assertTrue (Objects.requireNonNull(response.getBody()).size() > 0);
    }

    /**
     * Fetch work when sort param is none return bad request.
     */
    @Test
    public void fetchWork_WhenSortParamIsNone_ReturnBadRequest()
    {
        this.sortParam = "None";
        String url = String.format(FULL_URL + "/fetch?sort=%s&page=%d&size=%d", sortParam, pageParam, sizeParam);
        ResponseEntity<List> response = this.restTemplate.getForEntity(url, List.class);
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), response.getStatusCode().value());
        Assert.assertEquals(new ArrayList<>(), response.getBody());
    }
    //    =========== END FETCH API ==========

    /**
     * Update work when work is correct with id and name return ok.
     */
//    =========== START UPDATE API ==========
    @Test
    public void updateWork_WhenWorkIsCorrectWithIdAndName_ReturnOK()
    {
        workDto = saveDataTest(); // create data test to test
        workDto.setWorkName("updateWork_WhenWorkIsCorrect_ReturnOK");
        ResponseEntity<WorkDto> responseEntity = updateWork(workDto);
        Assert.assertEquals(workDto, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
    }

    /**
     * Update work when work status is none return created.
     */
    @Test
    public void updateWork_WhenWorkStatusIsNone_ReturnCreated()
    {
        workDto.setStatus("None");
        ResponseEntity<WorkDto> responseEntity = updateWork(workDto);
        Assert.assertEquals(workDto, responseEntity.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), responseEntity.getStatusCodeValue());
    }
    //    =========== END UPDATE API ==========

    /**
     * Delete work when id is correct return ok.
     */
//    =========== START DELETE API ==========
    @Test
    public void deleteWork_WhenIdIsCorrect_ReturnOK()
    {
        workDto = saveDataTest();
        ResponseEntity<WorkDto> response = deleteWork(workDto.getId());
        Assert.assertEquals(HttpStatus.OK.value(), response.getStatusCodeValue());
    }

    /**
     * Delete work when id not found return bad request.
     */
    @Test
    public void deleteWork_WhenIdNotFound_ReturnBadRequest()
    {
        Integer id = 1;
        if (workRepositoryReal.existsById(id)) {
            workRepositoryReal.deleteById(id);
        }
        ResponseEntity<WorkDto> response = deleteWork(id);
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
    }
    //    =========== END DELETE API ==========

    private ResponseEntity<WorkDto> updateWork(WorkDto workDto) {
        return restTemplate.exchange(
                FULL_URL + "/update",
                HttpMethod.PUT, new HttpEntity<WorkDto>(workDto, new HttpHeaders()), WorkDto.class);
    }

    private ResponseEntity<WorkDto> deleteWork(Integer id) {
        return restTemplate.exchange(
                String.format(FULL_URL + "/delete/%d", id),
                HttpMethod.DELETE, new HttpEntity<WorkDto>(workDto, new HttpHeaders()), WorkDto.class);
    }

    /**
     * Save data test work dto.
     *
     * @return the work dto
     */
    public WorkDto saveDataTest() {
        workDto.setWorkName("This is data test");
        workDto.setStatus("COMPLETE");
        WorkEntity workEntity = workRepositoryReal.save(modelMapper.map(workDto, WorkEntity.class));
        Assert.assertTrue(workRepositoryReal.existsById(workEntity.getId()));
        workDto.setId(workEntity.getId());
        return modelMapper.map(workEntity, WorkDto.class);
    }
}
