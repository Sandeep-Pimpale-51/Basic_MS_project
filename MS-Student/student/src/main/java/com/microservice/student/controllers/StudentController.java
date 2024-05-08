package com.microservice.student.controllers;

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

import com.microservice.student.Student;
import com.microservice.student.responses.StudentResponse;
import com.microservice.student.services.StudentService;

@RestController
@RequestMapping("/students")
public class StudentController {

	@Autowired
	private StudentService studentService;

//	create student
	@PostMapping
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		Student student1 = studentService.saveStudent(student);
		return ResponseEntity.status(HttpStatus.CREATED).body(student1);
	}

//	single student
	@GetMapping("/{id}")
	public ResponseEntity<StudentResponse> getSingleStudent(@PathVariable Long id) {
		StudentResponse student = studentService.getStudent(id);
		return ResponseEntity.ok(student);
	}

//	all student
	@GetMapping
	public ResponseEntity<List<Student>> getStudents() {
		List<Student> allStudents = studentService.getAllStudents();
		return ResponseEntity.ok(allStudents);
	}

//	delete student
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable long id) {
		String result = studentService.deleteStudent(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

//  update student
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Student student, @PathVariable long id) {

		Student studentOptional = studentService.UpdateStudent(id, student);

		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(studentOptional);
	}

}
