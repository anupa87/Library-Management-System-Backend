package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService userService;

  @PostMapping
  @ResponseBody
  public User addUser(@RequestBody User user) {
    return userService.addUser(user);
  }

  @GetMapping
  public List<User> getAllUsers() {
    return userService.getAllUsers();
  }

  @GetMapping("/{userId}")
  public ResponseEntity<User> getUserById(@PathVariable UUID userId) {
    User user = userService.getUserById(userId);
    if (user != null) {
      return ResponseEntity.ok(user);
    } else {
      throw new NotFoundException("User not found");
    }
  }

  @DeleteMapping("/{userId}")
  public ResponseEntity<?> deleteUser(@PathVariable UUID userId) {
    if (userService.getUserById(userId) != null) {
      userService.deleteUser(userId);
      return ResponseEntity.noContent().build();
    } else {
      throw new NotFoundException("User not found");
    }
  }

  @PutMapping("/{userId}")
  public ResponseEntity<User> updateUser(@PathVariable UUID userId, @RequestBody User user) {
    User foundUser = userService.getUserById(userId);
    if (foundUser != null) {
      User updatedUser = userService.updateUser(userId, user);
      return ResponseEntity.ok(updatedUser);
    } else {
      throw new NotFoundException("User not found");
    }
  }
}
