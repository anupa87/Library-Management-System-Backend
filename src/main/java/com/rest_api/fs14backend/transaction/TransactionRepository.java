package com.rest_api.fs14backend.transaction;

import com.rest_api.fs14backend.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TransactionRepository extends JpaRepository < Transaction, UUID > {
  List<Transaction> findByBookAndStatus(Book book, Transaction.BorrowStatus status);
}
