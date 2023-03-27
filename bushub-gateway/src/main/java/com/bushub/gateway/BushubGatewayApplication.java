package com.bushub.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class BushubGatewayApplication {

  public static void main(String[] args) {
    SpringApplication.run(BushubGatewayApplication.class, args);
  }

}
