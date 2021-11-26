package com.interview.workapp.repository;

import com.interview.workapp.entity.WorkEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The interface Work repository.
 */
@Repository
public interface WorkRepository extends JpaRepository<WorkEntity, Integer>, PagingAndSortingRepository<WorkEntity, Integer> {

}
