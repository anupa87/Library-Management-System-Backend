package com.rest_api.fs14backend.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private String firstName;
  private String lastName;
  private String email;
  private String password;
  private User.Role role;
}
