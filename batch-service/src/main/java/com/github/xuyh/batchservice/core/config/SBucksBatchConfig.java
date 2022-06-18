package com.github.xuyh.batchservice.core.config;

import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.ExitCodeMapper;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.boot.autoconfigure.batch.BasicBatchConfigurer;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.autoconfigure.transaction.TransactionManagerCustomizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SBucksBatchConfig extends BasicBatchConfigurer {
  private final DataSource dataSource;

  /**
   * Create a new {@link BasicBatchConfigurer} instance.
   *
   * @param properties the batch properties
   * @param dataSource the underlying data source
   * @param transactionManagerCustomizers transaction manager customizers (or {@code null})
   */
  protected SBucksBatchConfig(
      BatchProperties properties,
      DataSource dataSource,
      TransactionManagerCustomizers transactionManagerCustomizers) {
    super(properties, dataSource, transactionManagerCustomizers);
    this.dataSource = dataSource;
  }

  /**
   * 配置 JobLauncher
   *
   * @return
   * @throws Exception
   */
  @Override
  protected JobLauncher createJobLauncher() throws Exception {
    var jobLauncher = new SimpleJobLauncher();
    jobLauncher.setJobRepository(getJobRepository());
    jobLauncher.afterPropertiesSet();
    return jobLauncher;
  }

  /**
   * 配置 JobRepository
   *
   * @return
   * @throws Exception
   */
  @Override
  protected JobRepository createJobRepository() throws Exception {
    var factory = new JobRepositoryFactoryBean();
    factory.setDataSource(dataSource);
    factory.setDatabaseType("mysql");
    factory.setTransactionManager(getTransactionManager());
    factory.setIsolationLevelForCreate("ISOLATION_REPEATABLE_READ");
    // JobRepository元数据表的表前缀
    factory.setTablePrefix("BATCH_");
    factory.setMaxVarCharLength(1000);
    return factory.getObject();
  }

  /**
   * @return
   */
  @Bean
  ExitCodeMapper exitCodeMapper() {
    // TODO
    return str -> 0;
  }
}
