package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@Data
public class BookDTO {
  private UUID categoryId;
  private Integer ISBN;
  private String title;
  private String imageURL;
  private String description;
  private Author author;
  private String publisher;
  private String publishedYear;
  private Integer numberOfCopies;
  private Integer availableCopies;
}
