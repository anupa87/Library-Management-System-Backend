package com.rest_api.fs14backend.category;

import lombok.Data;

import java.util.UUID;

@Data
public class CategoryDTO {
  private UUID categoryId;
  private String name;
}
