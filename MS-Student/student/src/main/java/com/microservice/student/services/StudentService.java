package com.microservice.student.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.student.Student;
import com.microservice.student.externals.AddressResponseFeign;
import com.microservice.student.responses.AddressResponse;
import com.microservice.student.responses.StudentResponse;
import com.microservice.student.repos.StudentRepository;
import com.microservice.student.services.exceptions.ResourceNotFoundException;

@Service
public class StudentService {

	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private AddressResponseFeign addressResponseFeign;

	public Student saveStudent(Student student) {

		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {

		return studentRepository.findAll();
	}

	public StudentResponse getStudent(long id) {
//		return studentRepository.findById(id)
//				.orElseThrow(() -> new ResourceNotFoundException("Student with given Id not found :" + id));
		Student student=studentRepository.findById(id)
				
				.orElseThrow(() -> new ResourceNotFoundException("Student with given Id not found :" + id));
		AddressResponse addressResponse=addressResponseFeign.getSingleAddressResponse(student.getAddressID()).getBody();
		
		
		StudentResponse studentResponse = StudentResponse.builder().build();
		
		studentResponse.setId(student.getId());
		studentResponse.setName(student.getName());
		studentResponse.setDivision(student.getDivision());
		
		studentResponse.setAddress(addressResponse);
		return 	studentResponse;
	}

	public String deleteStudent(long id) {
		Student student= studentRepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("Student with given Id not found :" + id));

		studentRepository.delete(student);
		
		return "Student is deleted !!!!";
	}

	public Student UpdateStudent(long id, Student student) {
		studentRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Student with given Id not found :" + id));
		student.setId(id);
		return studentRepository.save(student);

	}

}
