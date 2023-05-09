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
  private UUID id;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String firstName;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String lastName;

  @Column(nullable = false,  columnDefinition = "varchar(50)")
  private String email;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String password;

  public User(String firstName, String lastName, String email, String password){
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.password = password;
  }
}
