package com.yonyk.talaria.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TalariaResourcesApplication {

  public static void main(String[] args) {
    SpringApplication.run(TalariaResourcesApplication.class, args);
  }
}
