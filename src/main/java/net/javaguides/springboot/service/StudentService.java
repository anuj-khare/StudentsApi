package net.javaguides.springboot.service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Student;

public interface StudentService {
	Student saveStudent(Student stud);
	Student getStudentById(long id);
	List<Student> getAllStudents();
	Student updateStudent(Student stud, long id);
}
