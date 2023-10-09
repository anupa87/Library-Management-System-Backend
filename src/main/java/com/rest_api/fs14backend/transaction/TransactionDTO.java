package com.rest_api.fs14backend.transaction;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;
import com.fasterxml.jackson.annotation.JsonFormat;

@Data
public class TransactionDTO {
  private UUID bookId;
  private UUID userId;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate borrowedDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate returnedDate;
  private Transaction.BorrowStatus status;
}
