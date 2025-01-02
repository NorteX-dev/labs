package com.nortexdev.lab7.services;

import com.nortexdev.lab7.models.Student;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface IStudentService {
	List<Student> getStudents();
	Student createStudent(Student student);
	Optional<Student> getStudentById(int id);
	Student updateStudent(int id, Student student);
	void deleteStudent(int id);
}
