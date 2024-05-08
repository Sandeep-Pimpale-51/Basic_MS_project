package com.microservice.student.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.student.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
