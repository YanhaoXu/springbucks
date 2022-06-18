package com.github.xuyh.batchservice.sbucks01.config;

import com.github.xuyh.batchservice.core.listener.SbucksJobListener;
import com.github.xuyh.batchservice.core.listener.SbucksStepListener;
import com.github.xuyh.batchservice.sbucks01.tasklet.Sbuks01Tasklet;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// @EnableBatchProcessing
public class Sbucks01Configuration {

  @Autowired private JobBuilderFactory jobBuilderFactory;

  @Autowired private StepBuilderFactory stepBuilderFactory;

  @Bean("sbucks01Job01")
  public Job sbucks01Job01(SbucksJobListener listener, Sbuks01Tasklet sbuks01Tasklet) {
    return jobBuilderFactory
        .get("sbucks01Job01")
        // .incrementer(new RunIdIncrementer())
        .listener(listener)
        .start(sbucks01Step01(sbuks01Tasklet))
        .build();
  }

  @Bean("sbucks01Step01")
  public Step sbucks01Step01(Sbuks01Tasklet tasklet) {
    return stepBuilderFactory
        .get("sbucks01Step01")
        .listener(new SbucksStepListener())
        .tasklet(tasklet)
        .build();
  }
}
