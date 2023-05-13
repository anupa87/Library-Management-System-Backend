package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rest_api.fs14backend.exceptions.NotFoundException;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
  @Autowired
  UserRepository userRepository;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtUtils jwtUtils;

  public User signup(User user) {
    User newUser = new User(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()), user.getRole());
    userRepository.save(newUser);
    return newUser;
  }

  public String login(AuthRequest authRequest){
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            )
    );
    User user = userRepository.findByEmail(authRequest.getEmail());
    return jwtUtils.generateToken(user);

  }

  public User save(User user) {
    return userRepository.save(user);
  }
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