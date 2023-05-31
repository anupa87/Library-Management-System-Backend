package com.rest_api.fs14backend.author;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table (name = "authors")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Author {
  @Id
  @UuidGenerator
  @GeneratedValue
  private UUID authorId;

  @Column (name = "fullName")
  private String fullName;

  @Column (name = "email")
  private String email;

  public Author(String fullName, String email) {
    this.fullName = fullName;
    this.email = email;
  }
}
