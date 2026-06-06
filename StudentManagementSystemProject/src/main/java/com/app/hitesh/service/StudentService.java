package com.app.hitesh.service;

import com.app.hitesh.dto.addStudent;
import com.app.hitesh.entity.Student;
import com.app.hitesh.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository sr;

    // Add Student
    public Student addStudent(Student std) {

        return sr.save(std);
    }

    // Delete Student
    public void deleteStudent(Integer id) {

        if(!sr.existsById(id)) {
        	throw new IllegalArgumentException("Student does not exist " + id);
        }

        sr.deleteById(id);
    }

    // Show All Students
    public List<Student> showStudent() {

        return sr.findAll();
    }

    // Get Student By Id
    public Optional<Student> getStudentById(Integer id) {

        return sr.findById(id);
    }

    // Update Student
    public Student updateStudent(Integer id,addStudent as) {

        Student s = sr.findById(id).orElseThrow(() ->new IllegalArgumentException("No Such Id exist in DB " + id));

        s.setName(as.getName());
        s.setCity(as.getCity());

        return sr.save(s);
    }

    // Partial Update
    public Student updatePartialStudent(Integer id,Map<String, Object> update) {

        Student s = sr.findById(id).orElseThrow(() -> new IllegalArgumentException("No Such Id exist in DB " + id));

        update.forEach((field, value) -> {

            switch(field) {

                case "name":
                    s.setName((String) value);
                    break;

                case "city":
                    s.setCity((String) value);
                    break;

                default:
                    throw new IllegalArgumentException(
                            "Invalid field : " + field);
            }
        });

        return sr.save(s);
    }

    // Search Student
    public List<Student> searchStudent(String name) {

        return sr.findByNameContaining(name);
    }
}