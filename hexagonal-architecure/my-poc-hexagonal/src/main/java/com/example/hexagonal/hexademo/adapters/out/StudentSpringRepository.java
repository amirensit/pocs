package com.example.hexagonal.hexademo.adapters.out;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StudentSpringRepository extends JpaRepository<StudentEntity, UUID> {

}
