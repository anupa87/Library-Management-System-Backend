package com.rest_api.fs14backend.book;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BookDTO {
  private UUID categoryId;
  private String title;
  private String imageURL;
  private String description;
  private UUID authorId;
  private String publisher;
  private LocalDate publishedDate;
  private Book.BookStatus status;

}
