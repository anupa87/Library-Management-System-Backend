package com.rest_api.fs14backend.transaction;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "transactions")
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Transaction {
  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID transactionId;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "book_id", nullable = false)
  private Book book;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  @Column(name = "borrow_date")
  private LocalDate borrowedDate;

  @Column(name = "return_date")
  private LocalDate returnedDate;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private BorrowStatus status;
  public Transaction(Book book, User user, LocalDate borrowedDate, LocalDate returnedDate, BorrowStatus status) {
    this.book = book;
    this.user = user;
    this.borrowedDate = borrowedDate;
    this.returnedDate = returnedDate;
    this.status = status;
  }

  public enum BorrowStatus {
    BORROWED, // The book is available for borrowing
    RETURNED // The book is not available (currently borrowed)
  }

}
