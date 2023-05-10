package com.rest_api.fs14backend.user;

import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor

public class User {
  @Id
  @GeneratedValue
  private UUID userId;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String lastName;

  @Column(unique = true, nullable = false, columnDefinition = "varchar(50)")
  private String email;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private Role role;

  public User(String firstName, String lastName, String email, String password, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
