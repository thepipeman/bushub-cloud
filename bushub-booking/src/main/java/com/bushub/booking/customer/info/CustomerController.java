package com.bushub.booking.customer.info;

import com.bushub.security.auth.UserJwtAuthentication;
import com.bushub.security.common.method.OnlyCustomerEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/customer/details")
@OnlyCustomerEndpoint
public class CustomerController {

  @GetMapping
  public String getUsername(UserJwtAuthentication authentication) {
    return authentication.getName();
  }
}
