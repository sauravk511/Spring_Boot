package com.springboot.crud.controller;

import com.springboot.crud.model.Employee;
import com.springboot.crud.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    // build create employee REST API
    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeRepository.save(employee);
    }

    // build get employee by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found" + id));
        return ResponseEntity.ok(employee);
    }

    //  build update employee REST API
    @PutMapping("{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found" + id));
        updatedEmployee.setFirstName(employeeDetails.getFirstName());
        updatedEmployee.setLastName(employeeDetails.getLastName());
        updatedEmployee.setEmailId(employeeDetails.getEmailId());

        employeeRepository.save(updatedEmployee);
        return ResponseEntity.ok(updatedEmployee);
    }

    // build delete employee REST API
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found" + id));
        employeeRepository.delete(employee);
        return ResponseEntity.ok("Employee deleted successfully");
    }
}
