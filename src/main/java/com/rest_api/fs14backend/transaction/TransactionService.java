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

    List<Transaction> borrowedTransactions = transactionRepository.findByBookAndStatus(book, Transaction.BorrowStatus.BORROWED);

    // Check if the book is already borrowed
    if (!borrowedTransactions.isEmpty()) {
      throw new IllegalStateException("The book is already borrowed.");
    }

    transactionDTO.setStatus(Transaction.BorrowStatus.BORROWED);

    Transaction transaction = new Transaction(
            book,
            user,
            transactionDTO.getBorrowedDate(),
            null,
            transactionDTO.getStatus()
    );
    Transaction savedTransaction = transactionRepository.save(transaction);
    // Update the book status to NOT_AVAILABLE
    book.setStatus(Book.BookStatus.NOT_AVAILABLE);
    bookRepository.save(book);

    return savedTransaction;
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

    // Check if the book has already been returned
    if (transaction.getStatus() == Transaction.BorrowStatus.RETURNED) {
      throw new IllegalStateException("The book has already been returned.");
    }

    LocalDate currentDate = LocalDate.now();
    transaction.setReturnedDate(currentDate);

    // Set the status to RETURNED
    transaction.setStatus(Transaction.BorrowStatus.RETURNED);

    transactionRepository.save(transaction);

    // Update the book status to AVAILABLE
    Book book = transaction.getBook();
    book.setStatus(Book.BookStatus.AVAILABLE);
    bookRepository.save(book);
  }
}