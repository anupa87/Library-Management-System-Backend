package com.rest_api.fs14backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.rest_api.fs14backend.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  public User addUser(User user) {
    return userRepository.save(user);
  }

  public List<User> getAllUsers() {
    return userRepository.findAll();
  }
  public User getUserById(UUID userId) {
    return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
  }

  public void deleteUser(UUID userId) {
    User foundUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));
    userRepository.delete(foundUser);
  }

  public User updateUser(UUID userId, User user) {
    User foundUser = userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User not found"));

    foundUser.setFirstName(user.getFirstName());
    foundUser.setLastName(user.getLastName());
    foundUser.setEmail(user.getEmail());
    foundUser.setPassword(user.getPassword());

    return userRepository.save(foundUser);
  }
}