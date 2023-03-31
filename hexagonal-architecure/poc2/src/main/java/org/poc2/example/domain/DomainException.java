package org.poc2.example.domain;

public class DomainException extends RuntimeException {
    DomainException(final String message) {
        super(message);
    }
}
