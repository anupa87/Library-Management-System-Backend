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

  public User createUser(User user) {
    return userRepository.save(user);
  }

  public List<User> findAll() {
    return userRepository.findAll();
  }
  public User findById(UUID id) {
    return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
  }

  public void deleteUser(UUID id) {
    User foundUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));
    userRepository.delete(foundUser);
  }

  public User updateUser(UUID id, User user) {
    User foundUser = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

    foundUser.setFirstName(user.getFirstName());
    foundUser.setLastName(user.getLastName());
    foundUser.setEmail(user.getEmail());
    foundUser.setPassword(user.getPassword());

    return userRepository.save(foundUser);
  }
}