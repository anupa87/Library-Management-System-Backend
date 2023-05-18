package com.rest_api.fs14backend.borrow;

import com.rest_api.fs14backend.user.User;
import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.book.BookMapper;
import com.rest_api.fs14backend.book.BookService;
import com.rest_api.fs14backend.book.BookDTO;
import com.rest_api.fs14backend.exceptions.NotFoundException;
import com.rest_api.fs14backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class BorrowService {
  @Autowired
  private BorrowRepository borrowRepository;

  @Autowired
  private BookService bookService;

  @Autowired
  private UserService userService;

  @Autowired
  private BorrowMapper borrowMapper;

  @Transactional
  public void borrowBook(UUID bookId, UUID userId) {
    Book book = bookService.getBookById(bookId);
    if (book.getAvailableCopies() > 0) {
      book.setAvailableCopies(book.getAvailableCopies() - 1);

      User user = userService.getUserById(userId);

      BorrowDTO borrowDTO = new BorrowDTO();
      borrowDTO.setBookId(bookId);
      borrowDTO.setUserId(userId);
      borrowDTO.setBorrowDate(LocalDate.now());
      borrowDTO.setReturnDate(LocalDate.now().plusWeeks(2));

      Borrow borrow = borrowMapper.toBorrow(borrowDTO, book, user);
      borrowRepository.save(borrow);
    } else {
      throw new NotFoundException("Book is not available for borrowing.");
    }
  }

  @Transactional
  public void returnBook(UUID borrowId) {
    Borrow borrow = borrowRepository.findById(borrowId)
            .orElseThrow(() -> new NotFoundException("Borrow record not found."));
    if (borrow.getReturnDate().isBefore(LocalDate.now())) {
      throw new NotFoundException("Book has already been returned.");
    }
    Book book = borrow.getBook();
    book.setAvailableCopies(book.getAvailableCopies() + 1);

    borrow.setReturnDate(LocalDate.now());
    borrowRepository.save(borrow);
  }

  public BorrowDTO getBorrowById(UUID borrowId) {
    Borrow borrow = borrowRepository.findById(borrowId)
            .orElseThrow(() -> new NotFoundException("Borrow record not found."));
    return borrowMapper.toBorrowDTO(borrow);
  }
}