package com.practice.springbatch4_practice;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatch4PracticeApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatch4PracticeApplication.class, args);
    }

}
