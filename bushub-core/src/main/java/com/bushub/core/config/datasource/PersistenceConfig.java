package com.bushub.core.config.datasource;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
public class PersistenceConfig {

  @Primary
  @Bean
  @ConfigurationProperties(prefix = "spring.datasource.hikari")
  public HikariDataSource dataSource(DataSourceProperties dataSourceProperties) {
    return (HikariDataSource) dataSourceProperties.initializeDataSourceBuilder()
      .build();
  }

}
