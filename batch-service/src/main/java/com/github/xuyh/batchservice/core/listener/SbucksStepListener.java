package com.github.xuyh.batchservice.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SbucksStepListener implements StepExecutionListener {

  @Override
  public void beforeStep(StepExecution stepExecution) {
    log.info(
        "Job:{}, Step:{} before...",
        stepExecution.getJobExecution().getJobId(),
        stepExecution.getStepName());
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    log.info(
        "Job:{}, Step:{} after...",
        stepExecution.getJobExecution().getJobId(),
        stepExecution.getStepName());
    if (!stepExecution.getExitStatus().getExitCode().equals(ExitStatus.FAILED.getExitCode())
        && stepExecution.getSkipCount() > 0) {
      return new ExitStatus("COMPLETED WITH SKIPS");
    } else {
      return null;
    }
  }
}
