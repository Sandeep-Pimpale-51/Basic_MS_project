package com.microservice.employee.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.employee.Employee;
import com.microservice.employee.responses.EmployeeResponse;
import com.microservice.employee.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

//	create employee
	@PostMapping
	public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee) {
		Employee employee1 = employeeService.saveEmployee(employee);
		return ResponseEntity.status(HttpStatus.CREATED).body(employee1);
	}

//	single employee
	@GetMapping("/{id}")
	public ResponseEntity<EmployeeResponse> getSingleEmployee(@PathVariable Long id) {
		EmployeeResponse employee = employeeService.getEmployee(id);
		return ResponseEntity.ok(employee);
	}

//	all employee
	@GetMapping
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> allEmployees = employeeService.getAllEmployees();
		return ResponseEntity.ok(allEmployees);
	}

//	delete employee
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable long id) {
		String result = employeeService.deleteEmployee(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

//  update employee
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

		Employee employeeOptional = employeeService.UpdateEmployee(id, employee);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(employeeOptional);
	}

}
