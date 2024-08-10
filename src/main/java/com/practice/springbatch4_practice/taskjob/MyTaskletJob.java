package com.practice.springbatch4_practice.taskjob;

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

@Configuration
@RequiredArgsConstructor
public class MyTaskletJob {
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private String JOB_NAME = "tasklet_Job";

    @Bean
    public Job taskletJob() {
        return jobBuilderFactory.get(JOB_NAME)
                .start(anonymousTaskletStep())
                .next(lambdaTaskletStep())
                .next(customTaskletStep())
                .build();
    }

    @Bean
    public Step anonymousTaskletStep() {
        return stepBuilderFactory.get("anonymousTaskletStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                        System.out.println("익명 클래스(Anonymous Class) 사용");
                        return RepeatStatus.FINISHED;
                    }
                }).build();
    }

    @Bean
    public Step lambdaTaskletStep() {
        return stepBuilderFactory.get("lambdaTaskletStep")
                .tasklet((contribution, chunkContext) -> {
                    System.out.println("람다(Lambda) 사용");
                    return RepeatStatus.FINISHED;
                }).build();
    }

    @Bean
    public Step customTaskletStep() {
        return stepBuilderFactory.get("customTaskletStep")
                .tasklet(new CustomTasklet())
                .build();
    }
}
