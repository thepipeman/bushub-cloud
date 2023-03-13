package com.bushub.booking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BushubBookingApplication {

  public static void main(String[] args) {
    SpringApplication.run(BushubBookingApplication.class, args);
  }

}
