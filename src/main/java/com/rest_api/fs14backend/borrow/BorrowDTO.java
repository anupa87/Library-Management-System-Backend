package com.rest_api.fs14backend.borrow;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class BorrowDTO {
  private UUID id;
  private UUID bookId;
  private UUID userId;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate borrowDate;
  @JsonFormat(pattern = "dd-MM-yyyy")
  private LocalDate returnDate;
}
