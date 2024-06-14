package com.nortexdev.lab7.controllers;

import com.nortexdev.lab7.exceptions.StudentNotFoundException;
import com.nortexdev.lab7.models.Student;
import com.nortexdev.lab7.services.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping("/students")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}

	@GetMapping("/students/{id}")
	public Student getStudentById(@PathVariable String id) {
		Student student = studentService.getStudentById(Integer.parseInt(id)).orElse(null);
		if(student == null) throw new StudentNotFoundException(HttpStatus.NOT_FOUND, "Student not found");
		return student;
	}

	@PostMapping("/students")
	public Student createStudent(@RequestBody Student student) {
		return studentService.createStudent(student);
	}

	@PutMapping("/students/{id}")
	public Student updateStudent(@PathVariable String id, @RequestBody Student student) {
		return studentService.updateStudent(Integer.parseInt(id), student);
	}

	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable String id) {
		studentService.deleteStudent(Integer.parseInt(id));
	}
}
