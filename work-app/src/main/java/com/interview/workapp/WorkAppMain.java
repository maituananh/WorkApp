package com.interview.workapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Work app main.
 */
@SpringBootApplication
@EntityScan(basePackages = {"com.interview.workapp.entity"})
@EnableJpaRepositories(basePackages = "com.interview.workapp.repository")
public class WorkAppMain {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(WorkAppMain.class, args);
    }
}
