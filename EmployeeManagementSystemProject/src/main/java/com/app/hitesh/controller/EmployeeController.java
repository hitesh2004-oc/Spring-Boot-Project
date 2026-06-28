package com.app.hitesh.controller;

import com.app.hitesh.dto.AddEmployee;
import com.app.hitesh.entity.Employee;
import com.app.hitesh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employee;

    // Add Employee
    @PostMapping("/employee")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee emp) {

        return ResponseEntity.ok(employee.addEmployee(emp));
    }

    // Show All Employees
    @GetMapping("/employee/show")
    public List<Employee> showEmployee() {

        return employee.showEmployee();
    }

    // Get Employee By Id
    @GetMapping("/employee/show/{id}")
    public Optional<Employee> getEmployeeById(@PathVariable Integer id) {

        return employee.getEmployeeById(id);
    }

    // Update Employee
    @PutMapping("/employee/update/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Integer id,
            @RequestBody AddEmployee ae) {

        return ResponseEntity.ok(
                employee.updateEmployee(id, ae));
    }

    // Partial Update
    @PatchMapping("/employee/partialUpdate/{id}")
    public ResponseEntity<Employee> updatePartialEmployee(@PathVariable Integer id, @RequestBody Map<String, Object> update) {

        return ResponseEntity.ok(
                employee.updatePartialEmployee(id, update));
    }

    // Delete Employee
    @DeleteMapping("/employee/delete/{id}")
    public void deleteEmployee(@PathVariable Integer id) {

        employee.deleteEmployee(id);
    }
}