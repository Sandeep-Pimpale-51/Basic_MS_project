package com.microservice.employee.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.employee.Employee;
import com.microservice.employee.externals.AddressResponseFeign;
import com.microservice.employee.repos.EmployeeRepository;
import com.microservice.employee.responses.AddressResponse;
import com.microservice.employee.responses.EmployeeResponse;
import com.microservice.employee.services.exceptions.ResourceNotFoundException;



@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private AddressResponseFeign addressResponseFeign;
	
	public Employee saveEmployee(Employee employee) {

		return employeeRepository.save(employee);
	}

	public List<Employee> getAllEmployees() {
		

		return employeeRepository.findAll();
	}


	public EmployeeResponse getEmployee(long id) {
	
		Employee employee=employeeRepository.findById(id)
				
				.orElseThrow(() -> new ResourceNotFoundException("Employee with given Id not found :" + id));
		AddressResponse addressResponse=addressResponseFeign.getSingleAddressResponse(employee.getAddressID()).getBody();
		
		
		EmployeeResponse employeeResponse = EmployeeResponse.builder().build();
		
		
		employeeResponse.setId(employee.getId());
		employeeResponse.setName(employee.getName());
		employeeResponse.setLocation(employee.getLocation());
		
		employeeResponse.setAddressResponse(addressResponse);
		return 	employeeResponse;
				}

	public String deleteEmployee(long id) {
		Employee employee= employeeRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Employee with given Id not found :" + id));

		employeeRepository.delete(employee);
		
		return "Employee is deleted !!!!";
	}

	public Employee UpdateEmployee(long id, Employee employee) {
		employeeRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with given Id not found :" + id));
		employee.setId(id);
		return employeeRepository.save(employee);

	}
}
