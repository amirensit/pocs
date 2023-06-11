package com.example.hexagonal.hexademo.errors.domain;

public class MissingMandatoryValueException extends AssertionException {

  private MissingMandatoryValueException(String fieldName, String errorCode, String message) {
    super(fieldName, errorCode, message);
  }

  public static MissingMandatoryValueException forNullValue(String fieldName, String errorCode) {
    return new MissingMandatoryValueException(fieldName, errorCode, defaultMessage(fieldName, "null"));
  }

  public static MissingMandatoryValueException forBlankValue(String field, String errorCode) {
    return new MissingMandatoryValueException(field, errorCode, defaultMessage(field, "blank"));
  }

  private static String defaultMessage(String field, String reason) {
    return new StringBuilder()
      .append("The field \"")
      .append(field)
      .append("\" is mandatory and wasn't set")
      .append(" (")
      .append(reason)
      .append(")")
      .toString();
  }

  @Override
  public AssertionErrorType type() {
    return AssertionErrorType.MISSING_MANDATORY_VALUE;
  }
}
