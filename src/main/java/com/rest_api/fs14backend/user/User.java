package com.rest_api.fs14backend.user;

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
  @Column(name = "id")
  private UUID id;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String lastName;

  @Column(nullable = false, unique = true, columnDefinition = "varchar(50)")
  private String email;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String password;

  @Enumerated (EnumType.STRING)
  @Column (nullable = false, columnDefinition = "varchar(50)")
  private Role role;

  public User(String firstName, String lastName, String email, String password, Role role){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
    this.role = role;
  }
}
