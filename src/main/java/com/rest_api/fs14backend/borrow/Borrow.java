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
  private UUID borrowId;

  @ManyToOne(optional = false)
  @JoinColumn(name = "book_id", referencedColumnName = "bookId")
  private Book book;

  @ManyToOne(optional = false)
  @JoinColumn(name = "user_id", referencedColumnName = "userId")
  private User borrower;

  @Column(nullable = false)
  private LocalDate borrowDate;

  @Column(nullable = false)
  private LocalDate returnDate;


  public Borrow(Book book, User borrower, LocalDate borrowDate, LocalDate returnDate) {
    this.book = book;
    this.borrower = borrower;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
  }
}
