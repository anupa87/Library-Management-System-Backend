package com.rest_api.fs14backend.transaction;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {
  public Transaction newTransaction(Book book, User user, TransactionDTO transactionDTO) {
    return new Transaction(
            book,
            user,
            transactionDTO.isBorrowed(),
            transactionDTO.getBorrowedDate(),
            transactionDTO.getReturnedDate()
    );
  }
}
