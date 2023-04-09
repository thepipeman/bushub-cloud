package com.bushub.booking.customer;

import com.bushub.security.auth.UserJwtAuthentication;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/details")
public class CustomerController {

  @GetMapping
  public String getUsername(UserJwtAuthentication authentication) {
    return authentication.getName();
  }
}
