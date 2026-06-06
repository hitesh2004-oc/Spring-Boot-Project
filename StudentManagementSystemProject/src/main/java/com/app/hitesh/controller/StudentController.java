package com.app.hitesh.controller;

import com.app.hitesh.dto.addStudent;
import com.app.hitesh.entity.Student;
import com.app.hitesh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class StudentController {

    @Autowired
    private StudentService student;

    // Add Student
    @PostMapping("/student")
    public Student addStudent(@RequestBody Student std) {

        return student.addStudent(std);
    }

    // Update Student
    @PutMapping("/student/update/{id}")
    public Student updateStudent(@PathVariable Integer id,@RequestBody addStudent as) {

        return student.updateStudent(id, as);
    }

    // Get Student By Id
    @GetMapping("/student/show/{id}")
    public Optional<Student> getStudentById(@PathVariable Integer id) {

        return student.getStudentById(id);
    }

    // Show All Students
    @GetMapping("/student/show")
    public List<Student> showStudent() {

        return student.showStudent();
    }

    // Partial Update
    @PatchMapping("/student/partialUpdate/{id}")
    public ResponseEntity<Student> updatePartialStudent(@PathVariable Integer id,@RequestBody Map<String, Object> update) {

        return ResponseEntity.ok(student.updatePartialStudent(id, update));
    }

    // Delete Student
    @DeleteMapping("/student/delete/{id}")
    public void deleteStudent(
            @PathVariable Integer id) {

        student.deleteStudent(id);
    }
}