package com.rest_api.fs14backend.transaction;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class TransactionDTO {
  private UUID bookId;
  private UUID userId;
  private boolean isBorrowed;
  private LocalDate borrowedDate;
  private LocalDate returnedDate;
}
