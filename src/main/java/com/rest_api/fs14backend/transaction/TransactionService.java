package com.rest_api.fs14backend.transaction;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookRepository;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.user.User;
import com.rest_api.fs14backend.user.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

  @Autowired
  private TransactionRepository transactionRepository;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public Transaction borrowBook(TransactionDTO transactionDTO) {
    UUID bookId = transactionDTO.getBookId();
    UUID userId = transactionDTO.getUserId();
    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new NotFoundException("Book not found"));

    User user = userRepository.findById(userId)
            .orElseThrow(() -> new NotFoundException("User not found"));

    Transaction transaction = new Transaction(
            book,
            user,
            true,
            transactionDTO.getBorrowedDate(),
            null
    );
    return transactionRepository.save(transaction);
  }

  public List<Transaction> getAllTransactions() {
    return transactionRepository.findAll();
  }

  public Transaction getTransactionById(UUID transactionId) {
    return transactionRepository.findById(transactionId).orElseThrow(() -> new NotFoundException("Transaction not found"));
  }

  @Transactional
  public void returnBook(UUID transactionId) {
    Transaction transaction = transactionRepository.findById(transactionId)
            .orElseThrow(() -> new NotFoundException("Transaction not found"));

    if (!transaction.isBorrowed()) {
      throw new IllegalStateException("Book is not borrowed");
    }
    transaction.setBorrowed(false);
    LocalDate currentDate = LocalDate.now();
    transaction.setReturnedDate(currentDate);

    transactionRepository.save(transaction);
  }
}