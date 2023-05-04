package com.example.hexagonal.hexademo.adapters.out;

import com.example.hexagonal.hexademo.domain.Student;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.UUID;

@Entity
@Table(name = "student")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentEntity {

    @Id
    private UUID id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private Long age;

    public static StudentEntity fromEntity(Student student) {
        return StudentEntity
                .builder()
                .id(student.getId())
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .age(student.getAge())
                .build();
    }

    public static Student toDomain(StudentEntity studentEntity) {
        return Student
                .builder()
                .id(studentEntity.getId())
                .firstName(studentEntity.getFirstName())
                .lastName(studentEntity.getLastName())
                .age(studentEntity.getAge())
                .build();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        StudentEntity other = (StudentEntity) obj;
        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
