package com.app.hitesh.service;

import com.app.hitesh.dto.AddEmployee;
import com.app.hitesh.entity.Employee;
import com.app.hitesh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository er;

 
    public Employee addEmployee(Employee emp) {
        return er.save(emp);
    }


    public List<Employee> showEmployee() {
        return er.findAll();
    }

   
    public Optional<Employee> getEmployeeById(Integer id) {
        return er.findById(id);
    }

    public void deleteEmployee(Integer id) {
        if(!er.existsById(id)) {
            throw new IllegalArgumentException(
                    "Employee does not exist " + id);
        }
        er.deleteById(id);
    }

    public Employee updateEmployee(Integer id,AddEmployee ae) {

        Employee e = er.findById(id).orElseThrow(() -> new IllegalArgumentException("No Such Id exist" + id));

        e.setName(ae.getName());
        e.setDepartment(ae.getDepartment());
        e.setSalary(ae.getSalary());
        e.setCity(ae.getCity());

        return er.save(e);
    }

    public Employee updatePartialEmployee(Integer id,Map<String, Object> update) {

        Employee e = er.findById(id).orElseThrow(() -> new IllegalArgumentException("No Such Id exist " + id));

        update.forEach((field, value) -> {

            switch(field) {

                case "name":
                    e.setName((String) value);
                    break;

                case "department":
                    e.setDepartment((String) value);
                    break;

                case "salary":
                    e.setSalary(
                            Double.valueOf(
                                    value.toString()));
                    break;

                case "city":
                    e.setCity((String) value);
                    break;

                default:
                    throw new IllegalArgumentException("Invalid field : " + field);
            }
        });

        return er.save(e);
    }

    // Search Employee
    public List<Employee> searchEmployee(String name) {

        return er.findByNameContaining(name);
    }
}