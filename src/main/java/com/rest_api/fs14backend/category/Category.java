package com.rest_api.fs14backend.category;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Category {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID categoryId;

  @Column(nullable = false, unique = true)
  private String name;

  public Category(String name){
    this.name = name;
  }
}