package com.rest_api.fs14backend.book;

import com.rest_api.fs14backend.author.Author;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "books")
@Data
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue
  private UUID bookId;

  @Column()
  @GeneratedValue
  private Integer ISBN;

  @Column(nullable = false, columnDefinition = "varchar(50)")
  private String title;

  @Column(nullable = false)
  private String imageURL;

  @Column(nullable = false, columnDefinition = "text")
  private String description;

  @ManyToMany (fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JoinTable(name = "books_author",joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "bookId"),
          inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "authorId"))
  private List<Author> authors;

  @Column(nullable = false)
  private String publisher;

  @Column(nullable = false)
  private String publishedYear;

  @Column(nullable = false)
  private Integer numberOfCopies;

  @Column(nullable = false)
  private Integer availableCopies;

  @Enumerated(EnumType.STRING)
  private Category category;

  public Book(String title, String imageURL, String description, List<Author> authors, String publisher, String publishedYear, Integer numberOfCopies, Integer availableCopies, Category category) {
    this.title = title;
    this.imageURL = imageURL;
    this.description = description;
    this.authors = authors;
    this.publisher = publisher;
    this.publishedYear = publishedYear;
    this.numberOfCopies = numberOfCopies;
    this.availableCopies = availableCopies;
    this.category = category;
  }
}

