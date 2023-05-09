package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.createUser(user);
  }

  @GetMapping
  public List<User> findAll() {
    return userService.findAll();
  }

  @GetMapping("/{id}")
  public User findById(@PathVariable UUID id) {
    System.out.println("Users test before.........");
    User user = userService.findById(id);
System.out.println("Users test");
    if (user == null) {
      throw new NotFoundException("User not found");
    }
    return user;
  }
}
