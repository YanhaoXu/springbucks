package com.github.xuyh.batchservice;

import com.github.xuyh.batchservice.core.utils.AppContextUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.converter.DefaultJobParametersConverter;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

import java.util.Arrays;

@Slf4j
@SpringBootApplication
@EnableBatchProcessing
public class BatchServiceApplication implements CommandLineRunner, ExitCodeGenerator {

  @Autowired private JobLauncher launcher;

  private int exitCode;

  public static void main(String[] args) {
    ApplicationContext context = SpringApplication.run(BatchServiceApplication.class, args);
    System.exit(SpringApplication.exit(context));
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("args...{}", Arrays.toString(args));
    var properties = StringUtils.splitArrayElementsIntoProperties(args, "=");
    String jobName = properties.getProperty("JobName");
    log.info("JobName:{}", jobName);
    var jobParameters = new DefaultJobParametersConverter().getJobParameters(properties);
    JobParameters allParam =
        new JobParametersBuilder()
            .addLong("time", System.currentTimeMillis())
            .addJobParameters(jobParameters)
            .toJobParameters();
    var job = AppContextUtils.getBean(jobName, Job.class);

    var jobExecution = this.launcher.run(job, allParam);

    log.info("Job Status:{}", jobExecution.getStatus());

    if (BatchStatus.COMPLETED != jobExecution.getStatus()) exitCode = 1;

    log.info("Job ExitCode:{}", exitCode);
  }

  @Override
  public int getExitCode() {
    return exitCode;
  }
}
