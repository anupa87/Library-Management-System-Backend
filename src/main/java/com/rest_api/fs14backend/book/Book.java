package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import com.rest_api.fs14backend.category.Category;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @UuidGenerator
  @GeneratedValue
  private UUID id;

  @Column(nullable = false)
  private Integer ISBN;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String title;

  @Column(nullable = false)
  private String imageURL;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @ManyToMany
  private List<Author> author;

  @Column(nullable = false)
  private String publisher;

  @Column(nullable = false)
  private String publishedYear;

  @Column(nullable = false)
  private Integer numberOfCopies;

  @Column(nullable = false)
  private Integer availableCopies;

  @ManyToOne(optional = false)
  private Category category;

  public Book(Integer ISBN, String title, String imageURL, String description, Author author, String publisher, String publishedYear, Integer numberOfCopies, Integer availableCopies, Category category) {
    this.ISBN = ISBN;
    this.title = title;
    this.imageURL = imageURL;
    this.description = description;
    this.author = (List<Author>) author;
    this.publisher = publisher;
    this.publishedYear = publishedYear;
    this.numberOfCopies = numberOfCopies;
    this.availableCopies = availableCopies;
    this.category = category;
  }
}

