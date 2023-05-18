package com.rest_api.fs14backend.borrow;

import com.rest_api.fs14backend.book.Book;
import com.rest_api.fs14backend.user.User;
import org.springframework.stereotype.Component;

@Component
public class BorrowMapper {

  public BorrowDTO toBorrowDTO(Borrow borrow) {
    BorrowDTO borrowDTO = new BorrowDTO();
    borrowDTO.setBookId(borrow.getBook().getBookId());
    borrowDTO.setUserId(borrow.getBorrower().getUserId());
    borrowDTO.setBorrowDate(borrow.getBorrowDate());
    borrowDTO.setReturnDate(borrow.getReturnDate());
    return borrowDTO;
  }

  public Borrow toBorrow(BorrowDTO borrowDTO, Book book, User user) {
    Borrow borrow = new Borrow();
    borrow.setBook(book);
    borrow.setBorrower(user);
    borrow.setBorrowDate(borrowDTO.getBorrowDate());
    borrow.setReturnDate(borrowDTO.getReturnDate());
    return borrow;
  }
}
