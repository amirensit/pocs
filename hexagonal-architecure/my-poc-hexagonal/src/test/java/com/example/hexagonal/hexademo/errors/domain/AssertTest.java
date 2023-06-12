package com.example.hexagonal.hexademo.errors.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssertTest {

    @Test
    void givenEmptyCollection_shouldThrowException() {
        List emptyList = Collections.EMPTY_LIST;
        Assertions.assertThatThrownBy(() -> Assert.notEmpty("attributeName", emptyList))
                .isInstanceOf(MissingMandatoryValueException.class)
                .hasMessage("The field \"attributeName\" is mandatory and wasn't set (empty)");
    }

}