package com.bushub.core.management.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping
  public ResponseEntity<Long> create(@RequestBody @Valid User user) {
    final var id = userService.create(user);
    return new ResponseEntity<>(id, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<List<User>> readAll() {
    final var users = userService.readAll();
    if (CollectionUtils.isEmpty(users)) {
      return ResponseEntity.noContent().build();
    }

    return ResponseEntity.ok(users);
  }

}
