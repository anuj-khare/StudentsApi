package net.javaguides.springboot.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	//Since there is only one constructor so there is no need to write @Autowired
	private StudentRepository studRepo;	
	public StudentServiceImpl(StudentRepository studRepo) {
		super();
		this.studRepo = studRepo;
	}
	@Override
	public Student saveStudent(Student stud) {
		return studRepo.save(stud);
	}
	@Override
	public Student getStudentById(long id) {
		return studRepo.findById(id).orElseThrow(
		()->new ResourceNotFoundException("Student","id",id)
				);
	}
	@Override
	public List<Student> getAllStudents() {
		// TODO Auto-generated method stub
		return this.studRepo.findAll();
	}
	public Student updateStudent(Student stud, long id) {
		Student ExistingStudent = this.studRepo.findById(id).orElseThrow(
				()->new ResourceNotFoundException("Student","id",id));
		ExistingStudent.setFirstName(stud.getFirstName());
		ExistingStudent.setLastName(stud.getLastName());
		ExistingStudent.setEmail(stud.getEmail());
		return this.studRepo.save(ExistingStudent);
		
	}
	
	public void DeleteStudent(long id) {
		Student Existing_Student = this.studRepo.findById(id).orElseThrow(
				()-> new ResourceNotFoundException("Student","id",id));
		this.studRepo.delete(Existing_Student);
	}
	


}


//starting with spring 4.3,if a class which is configured as Spring Bean has only one constructor,
//then @Autowired anno can be ommited and spring will use that constructor and inject all the 
//necessary dependencies.
