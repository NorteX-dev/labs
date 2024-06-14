package com.nortexdev.lab7.repositories;

import com.nortexdev.lab7.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Integer> { }
