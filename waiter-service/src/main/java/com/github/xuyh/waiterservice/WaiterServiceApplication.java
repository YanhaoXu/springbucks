package com.github.xuyh.waiterservice;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.TimeZone;

@SpringBootApplication
public class WaiterServiceApplication implements WebMvcConfigurer {

  public static void main(String[] args) {
    SpringApplication.run(WaiterServiceApplication.class, args);
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    //    registry.addInterceptor(new PerformanceInteceptor)
  }

  @Bean
  public Hibernate5Module hibernate5Module() {
    return new Hibernate5Module();
  }

  public Jackson2ObjectMapperBuilderCustomizer jacksonBuilderCustomizer() {
    return builder -> builder.indentOutput(true).timeZone(TimeZone.getTimeZone("Asia/Shanghai"));
  }
}
