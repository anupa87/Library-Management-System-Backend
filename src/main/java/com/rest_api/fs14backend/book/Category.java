package com.rest_api.fs14backend.book;

public enum Category {
  HISTORY,
  SCIENCE_FICTION,
  FANTASY,
  BIOGRAPHY,
  ROMANCE,
  MYSTERY,
  THRILLER,
  HORROR;

  private final String value;

  Category() {
    this.value = name().toLowerCase();
  }

  Category(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}