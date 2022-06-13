package com.github.xuyh.batchservice.core.config;

import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.sql.DataSource;

public class BatchConfig extends DefaultBatchConfigurer {
  @Autowired private DataSource dataSource;
}
