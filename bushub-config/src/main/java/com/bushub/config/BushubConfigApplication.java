package com.bushub.config;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class BushubConfigApplication {

  public static void main(String[] args) {
    SpringApplication.run(BushubConfigApplication.class, args);
  }

}
