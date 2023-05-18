package com.rest_api.fs14backend.borrow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/borrows")
public class BorrowController {
  @Autowired
  private BorrowService borrowService;
  @GetMapping("/{borrowId}")
  public BorrowDTO getBorrowById(@PathVariable UUID borrowId) {
    return borrowService.getBorrowById(borrowId);
  }
  @PostMapping
  public void borrowBook(@RequestParam UUID bookId, @RequestParam UUID userId) {
    borrowService.borrowBook(bookId,userId);
  }

}
