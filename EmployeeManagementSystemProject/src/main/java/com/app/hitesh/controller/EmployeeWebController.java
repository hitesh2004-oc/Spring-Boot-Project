package com.app.hitesh.controller;

import com.app.hitesh.entity.Employee;
import com.app.hitesh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class EmployeeWebController {

    @Autowired
    private EmployeeService employee;

    // Home Page
    @GetMapping("/")
    public String home(Model model) {

        model.addAttribute("employees",
                employee.showEmployee());

        return "index";
    }

    // Add Page
    @GetMapping("/add")
    public String addPage(Model model) {

        model.addAttribute("employee", new Employee());

        return "add-employee";
    }

    // Save (Create + Update)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee emp) {

        employee.addEmployee(emp); // save() handles insert + update

        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editEmployee(@PathVariable Integer id, Model model) {

        Employee emp = employee.getEmployeeById(id).orElseThrow(() ->
                        new RuntimeException("Employee not found"));

        model.addAttribute("employee", emp);

        return "add-employee"; 
    }

    // Delete
    @GetMapping("/delete/{id}")
    public String deleteEmployee(@PathVariable Integer id) {

        employee.deleteEmployee(id);

        return "redirect:/";
    }

    // Search
    @GetMapping("/search")
    public String searchEmployee(@RequestParam String keyword,
                                 Model model) {

        model.addAttribute("employees",
                employee.searchEmployee(keyword));

        return "index";
    }
}