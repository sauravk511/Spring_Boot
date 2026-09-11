package com.springboot.crud.repository;

import com.springboot.crud.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long>{

    // All CRUD database methods


}
