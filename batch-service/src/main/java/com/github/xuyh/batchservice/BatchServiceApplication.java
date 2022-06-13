package com.github.xuyh.batchservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class BatchServiceApplication {

  public static void main(String[] args) {
    log.info("Start BatchServiceApplication...");
    SpringApplication.run(BatchServiceApplication.class, args);
  }
}
