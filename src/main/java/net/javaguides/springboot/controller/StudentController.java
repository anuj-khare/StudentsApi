package net.javaguides.springboot.controller;

import java.util.List;

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

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.service.impl.StudentServiceImpl;

@RestController
@RequestMapping("/students")
public class StudentController {
	
	private StudentServiceImpl studService; 
	public StudentController(StudentServiceImpl studService) {
		super();
		this.studService= studService;
	}
	@PostMapping
	public ResponseEntity<Student> saveEmployee(@RequestBody Student stud){
		return new ResponseEntity<Student>(this.studService.saveStudent(stud),HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Student> getStudByName(@PathVariable("id") long id){
		return new ResponseEntity<Student>(this.studService.getStudentById(id),HttpStatus.OK);
	}
	@GetMapping
	public ResponseEntity<List<Student>> getAllStudents(){
		return new ResponseEntity<List<Student>>(studService.getAllStudents(),HttpStatus.OK);
	}
	
	@PutMapping("{id}")
	public ResponseEntity<Student> updateStudent(@RequestBody Student stud,@PathVariable("id") long id){
		return new ResponseEntity<Student>(this.studService.updateStudent(stud,id),HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") long id){
		this.studService.DeleteStudent(id);
		return new ResponseEntity<String>("Deleted succesfully",HttpStatus.OK);
	}
}


//@RestController is a convenient annotation that combines @Controller and @ResponseBody which 
//eliminates the need to annotate every request handling method of the controller
//class with @ResponseBody annotation.