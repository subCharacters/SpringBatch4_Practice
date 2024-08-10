package com.practice.springbatch4_practice.config;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class TaskletJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private String JOB_NAME = "testTaskMyJob";

    @Bean
    public Job testTaskMyJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(testStep())
                .build();
    }

    @Bean
    public Step testStep() {
        return stepBuilderFactory.get("step1")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        List<String> items = new ArrayList<>();
                        for (int i = 1; i <= 100; i++) {
                            items.add("No." + i);
                        }
                        System.out.println(items);
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }
}
