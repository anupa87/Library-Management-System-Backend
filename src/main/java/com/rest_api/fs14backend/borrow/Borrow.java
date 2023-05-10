package com.rest_api.fs14backend.borrow;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "borrows")
@Data
@NoArgsConstructor
public class Borrow {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  @ManyToOne(optional = false)
  @JoinColumn(name = "book_id", referencedColumnName = "bookId")
  private Book book;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User user;

  @Column(nullable = false)
  private LocalDate borrowDate;

  @Column(nullable = false)
  private LocalDate returnDate;

  @Column(nullable = false)
  private String status;

  public Borrow(Book book, User user, LocalDate borrowDate, LocalDate returnDate, String status) {
    this.book = book;
    this.user = user;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
    this.status = status;
  }
}
