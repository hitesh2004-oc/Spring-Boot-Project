package com.app.hitesh.repository;

import com.app.hitesh.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    // Search Employee
    List<Employee> findByNameContaining(String name);
}