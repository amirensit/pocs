package com.example.hexagonal.hexademo.errors.domain;

import java.util.Map;

public abstract class AssertionException extends RuntimeException {

  private final String fieldName;

  private final String errorCode;

  protected AssertionException(String field, String errorCode, String message) {
    super(message);
    this.fieldName = field;
    this.errorCode = errorCode;
  }

  public abstract AssertionErrorType type();

  public String getFieldName() {
    return fieldName;
  }

  public String getErrorCode() {
    return errorCode;
  }

  public Map<String, String> parameters() {
    return Map.of();
  }
}
