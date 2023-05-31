package com.rest_api.fs14backend.user;

import com.rest_api.fs14backend.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.rest_api.fs14backend.user.User.Role;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  public Map <String, String> login(@RequestBody AuthRequest authRequest){
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    authRequest.getEmail(),
                    authRequest.getPassword()
            )
    );
    User user = userRepository.findByEmail(authRequest.getEmail());
    Map<String, String> token = new HashMap<>();
    token.put("token", jwtUtils.generateToken(user));
    return token;
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

    String existingPassword = foundUser.getPassword();
    Role existingRole = foundUser.getRole();

    foundUser.setFirstName(user.getFirstName());
    foundUser.setLastName(user.getLastName());
    foundUser.setEmail(user.getEmail());
    foundUser.setPassword(existingPassword);
    foundUser.setRole(existingRole);

    return userRepository.save(foundUser);
  }
}