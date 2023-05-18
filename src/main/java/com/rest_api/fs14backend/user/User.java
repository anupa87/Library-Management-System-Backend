package com.rest_api.fs14backend.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor

public class User {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID userId;

  @Column(nullable = false, columnDefinition = "varchar(250)")
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(250)")
  private String lastName;

  @Column(unique = true, nullable = false, columnDefinition = "varchar(250)")
  private String email;

  @Column(nullable = false, columnDefinition = "varchar(250)")
  @JsonProperty (access = JsonProperty.Access.WRITE_ONLY)
  private String password;

  @Enumerated(EnumType.STRING)
  @Column
  private Role role;

  public User(String firstName, String lastName, String email, String password, Role role) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }

  enum Role {
    USER,
    ADMIN
  }
}
