package com.app.hitesh.repository;

import com.app.hitesh.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    // Search Student By Name
    List<Student> findByNameContaining(String name);
}