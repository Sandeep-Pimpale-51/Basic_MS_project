package com.microservice.employee.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.employee.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{

}
