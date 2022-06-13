package com.github.xuyh.batchservice.sbucks01.tasklet;

import com.github.xuyh.batchservice.core.mapper.TCoffeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@StepScope
@Component
@Slf4j
public class Sbuks01Tasklet implements Tasklet {

  @Autowired private TCoffeeMapper tCoffeeMapper;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws Exception {
    log.info("Start Sbuks01Tasklet");
    tCoffeeMapper.deleteAll();

    return RepeatStatus.FINISHED;
  }
}
