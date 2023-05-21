package com.example.hexagonal.hexademo.errors.domain;

import java.util.Optional;

public class Assert {

    private Assert() {}

    public static void notNull(String fieldName, Object fieldValue) {
        if (Optional.ofNullable(fieldValue).isEmpty()) {
            throw MissingMandatoryValueException.forNullValue(fieldName, fieldName + "." + "null");
        }
    }
}
