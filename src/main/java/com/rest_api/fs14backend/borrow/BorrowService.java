package com.rest_api.fs14backend.borrow;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class BorrowService {
  @Autowired
  BorrowRepository borrowRepository;

  @Autowired
  BookService bookService;

  @Autowired
  UserService userService;

  public void borrowBook(UUID bookId, UUID userId) {
    Book book = bookService.getBookById(bookId);
    if (book.getAvailableCopies() > 0) {
      book.setAvailableCopies(book.getAvailableCopies() - 1);
      bookService.updateBook(bookId, book);
      Borrow borrow = new Borrow();
      borrow.setBook(book);
      borrow.setUser(userService.getUserById(userId));
      borrow.setBorrowDate(LocalDate.now());
      borrow.setStatus("BORROWED");
      borrowRepository.save(borrow);
    } else {
      throw new NotFoundException ("Book is not available for borrowing.");
    }
  }

  public void returnBook( UUID borrowId) {
    Borrow borrow = borrowRepository.findById(borrowId)
            .orElseThrow(() -> new NotFoundException ("Borrow record not found."));
    if (borrow.getStatus().equals("BORROWED")) {
      Book book = borrow.getBook();
      book.setAvailableCopies(book.getAvailableCopies() + 1);
      bookService.updateBook(book.getBookId(), book);
      borrow.setReturnDate(LocalDate.now());
      borrow.setStatus("RETURNED");
      borrowRepository.save(borrow);
    } else {
      throw new NotFoundException ("Book has already been returned.");
    }
  }
}

