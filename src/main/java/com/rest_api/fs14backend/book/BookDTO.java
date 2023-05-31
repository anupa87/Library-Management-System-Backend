package com.rest_api.fs14backend.book;

import lombok.Data;

import java.util.UUID;

@Data
public class BookDTO {
  private UUID categoryId;
  private String title;
  private String imageURL;
  private String description;
  private UUID authorId;
  private String publisher;
  private String publishedYear;
 private Book.Status status;
}
