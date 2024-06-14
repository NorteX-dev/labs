package com.nortexdev.lab7.services;

import com.nortexdev.lab7.models.Student;
import com.nortexdev.lab7.repositories.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService implements IStudentService {
	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student createStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Optional<Student> getStudentById(int id) {
		return studentRepository.findById(id);
	}

	@Override
	public Student updateStudent(int id, Student student) {
		Optional<Student> existingStudent = studentRepository.findById(id);
		if (existingStudent.isPresent()) {
			Student unwrappedStudent = existingStudent.get();
			unwrappedStudent.setName(student.getName());
			unwrappedStudent.setLastName(student.getLastName());
			unwrappedStudent.setIndexNumber(student.getIndexNumber());
			unwrappedStudent.setEmail(student.getEmail());
			return studentRepository.save(unwrappedStudent);
		}
		return null;
	}

	@Override
	public void deleteStudent(int id) {
		studentRepository.deleteById(id);
	}
}
