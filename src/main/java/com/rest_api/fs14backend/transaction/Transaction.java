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

  @Column(name = "is_borrowed", nullable = false)
  private boolean isBorrowed;

  @Column(name = "borrow_date")
  private LocalDate borrowedDate;

  @Column(name = "return_date")
  private LocalDate returnedDate;

  public Transaction(Book book, User user, boolean isBorrowed, LocalDate borrowedDate, LocalDate returnedDate) {
    this.book = book;
    this.user = user;
    this.isBorrowed = isBorrowed;
    this.borrowedDate = borrowedDate;
    this.returnedDate = returnedDate;
  }

}
