package com.bushub.core.management.user;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public Long create(User user) {
    final var userCreated = userRepository.save(user);
    log.trace("User created [id=<{}>]", userCreated.getId());
    return userCreated.getId();
  }

  public List<User> readAll() {
    return Lists.newArrayList(userRepository.findAll());
  }

}
