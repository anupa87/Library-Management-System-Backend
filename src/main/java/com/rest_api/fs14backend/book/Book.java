package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.category.Category;
import jakarta.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID bookId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String title;

  @Column(nullable = false)
  private String imageURL;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "author_id", nullable = false)
  private Author author;

  @Column(nullable = false)
  private String publisher;

  @Column(nullable = false)
  private String publishedYear;

  @Column(nullable = false)
  private Integer numberOfCopies;

  @Column(nullable = false)
  private Integer availableCopies;

  public Book( Category category, String title, String imageURL, String description, Author author, String publisher, String publishedYear, Integer numberOfCopies, Integer availableCopies) {
    this.category = category;
    this.title = title;
    this.imageURL = imageURL;
    this.description = description;
    this.author = author;
    this.publisher = publisher;
    this.publishedYear = publishedYear;
    this.numberOfCopies = numberOfCopies;
    this.availableCopies = availableCopies;
  }
}

