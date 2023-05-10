package com.rest_api.fs14backend.user;

public enum Role {
  USER("user"),
  ADMIN("admin");

  private final String value;

  Role(String value) {
    this.value = value;
  }

  @Override
  public String toString() {
    return value;
  }
}