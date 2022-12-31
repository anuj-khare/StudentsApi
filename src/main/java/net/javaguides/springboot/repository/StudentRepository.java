package net.javaguides.springboot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.javaguides.springboot.model.Student;

//@Repository -> No need to write this annotation as jpa repository internally implements this already
public interface StudentRepository extends JpaRepository<Student,Long>{
//	public Student findByName(String name);
}
