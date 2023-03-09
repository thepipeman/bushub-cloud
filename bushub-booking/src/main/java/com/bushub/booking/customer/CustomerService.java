package com.bushub.booking.customer;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;

  public Long create(Customer customer) {
    final var customerCreated = customerRepository.save(customer);
    log.trace("Customer created [id=<{}>]", customerCreated.getId());
    return customerCreated.getId();
  }

  public Customer readById(long id) {
    return customerRepository.findById(id).orElse(null);
  }
}
