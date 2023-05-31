package com.rest_api.fs14backend.borrow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class BorrowController {
  @Autowired
  private BorrowService borrowService;

  @GetMapping ("/borrows")
  public List<BorrowDTO> getAllBorrows() {
    return borrowService.getAllBorrows();
  }
  @GetMapping("/borrow/{borrowId}")
  public BorrowDTO getBorrowById(@PathVariable UUID borrowId) {
    return borrowService.getBorrowById(borrowId);
  }
  @PostMapping ("/borrow")
  public void borrowBook(@RequestParam UUID bookId, @RequestParam UUID userId) {
    borrowService.borrowBook(bookId,userId);
  }

  @PostMapping("/return")
  public void returnBook(@RequestParam UUID borrowId) {
    borrowService.returnBook(borrowId);
  }
}
