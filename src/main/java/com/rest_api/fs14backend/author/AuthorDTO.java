package com.rest_api.fs14backend.author;

import lombok.Data;

import java.util.UUID;

@Data
public class AuthorDTO {
  private UUID authorId;
  private String fullName;
  private String email;
}
