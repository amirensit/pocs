package com.example.hexagonal.hexademo.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class StudentTest {

    @Test
    void setIdAttributeUsingBuilderTest() {
        UUID uuid = UUID.randomUUID();
        Student.Builder builder = new Student.Builder();
        builder.id(uuid);
        Student student = builder.build();
        Assertions.assertThat(student.getId()).isEqualTo(uuid);
    }

    @Test
    void setFirstNameAttributeUsingBuilderTest() {
        String firstName = "test-firstName";
        Student.Builder builder = new Student.Builder();
        builder.firstName(firstName);
        Student student = builder.build();
        Assertions.assertThat(student.getFirstName()).isEqualTo(firstName);
    }

    @Test
    void setLastNameAttributeUsingBuilderTest() {
        String lastName = "test-lastName";
        Student.Builder builder = new Student.Builder();
        builder.lastName(lastName);
        Student student = builder.build();
        Assertions.assertThat(student.getLastName()).isEqualTo(lastName);
    }

    @Test
    void setAgeAttributeUsingBuilderTest() {
        Long age = 28L;
        Student.Builder builder = new Student.Builder();
        builder.age(age);
        Student student = builder.build();
        Assertions.assertThat(student.getAge()).isEqualTo(age);
    }
}