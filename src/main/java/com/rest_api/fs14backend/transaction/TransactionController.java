package com.rest_api.fs14backend.transaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class TransactionController {
  @Autowired
  private TransactionService transactionService;

  @PostMapping("/transactions/borrow")
  public Transaction borrowBook(@RequestBody TransactionDTO transactionDTO) {
    return transactionService.borrowBook(transactionDTO);
  }

  @GetMapping("/transactions")
  public List<Transaction> getAllTransactions() {
    return transactionService.getAllTransactions();
  }

  @GetMapping("/transactions/{transactionId}")
  public Transaction getTransactionById(@PathVariable UUID transactionId) {
   return transactionService.getTransactionById(transactionId);
  }

  @PutMapping("/transactions/{transactionId}/return")
  public void returnBook(@PathVariable UUID transactionId) {
    transactionService.returnBook(transactionId);
  }
}
