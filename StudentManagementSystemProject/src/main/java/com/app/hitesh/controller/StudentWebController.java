package com.app.hitesh.controller;

import com.app.hitesh.entity.Student;
import com.app.hitesh.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class StudentWebController {

    @Autowired
    private StudentService student;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute(
                "students",
                student.showStudent());

        return "index";
    }

    // Open Add Page
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute(
                "student",
                new Student());

        return "add-student";
    }

    // Save Student
    @PostMapping("/save")
    public String saveStudent(
            @ModelAttribute Student std) {

        student.addStudent(std);

        return "redirect:/";
    }

    // Open Update Page
    @GetMapping("/edit/{id}")
    public String editPage(
            @PathVariable Integer id,
            Model model) {

        Student s = student
                .getStudentById(id)
                .orElseThrow();

        model.addAttribute(
                "student",
                s);

        return "update-student";
    }

    // Update Student--
    @PostMapping("/update/{id}")
    public String updateStudent(
            @PathVariable Integer id,
            @ModelAttribute Student std) {

        Student s = student
                .getStudentById(id)
                .orElseThrow();

        s.setName(std.getName());
        s.setCity(std.getCity());

        student.addStudent(s);

        return "redirect:/";
    }

    // Search Students----
    @GetMapping("/search")
    public String searchStudent(
            @RequestParam String keyword,
            Model model) {

        model.addAttribute(
                "students",
                student.searchStudent(keyword));

        return "index";
    }
    
    // Delete Student--
    @GetMapping("/delete/{id}")
    public String deleteStudent(
            @PathVariable Integer id) {

        student.deleteStudent(id);

        return "redirect:/";
    }
}