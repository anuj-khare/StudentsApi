package net.javaguides.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import jakarta.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;

//Only scans Entities and Repositories,doesn't scan services and controllers etc
//@ContextConfiguration(StudentRepository.class)
//@RunWith(SpringJUnit5ClassRunner.class)


@DataJpaTest
@ExtendWith(SpringExtension.class)
//@Transactional

public class StudentRepositoryTest {
	@Autowired
	private StudentRepository studRepo;
	private Student stud;
	
	@BeforeEach
	public void setup() {
		stud = new Student();	
		stud.setFirstName("Anuj");
		stud.setLastName("Khare");
		stud.setEmail("anuj@gmail.com");
	}
	
	@Test
	public void givenStudentObject_whenSave_thenReturnSavedStudent() {
		//given - precondition or setup
		//we already have a student in beforeEach method 
		//when - action or behaviour that is to be tested
		Student savedStudent = studRepo.save(stud);
		//then - verifying  the result
		assertThat(savedStudent).isNotNull();
		assertThat(savedStudent.getId()).isGreaterThan(0);
	}
	@Test
	public void givenStudentObject_whenUpdate_thenReturnUpdatedStudent() {
		//given
		
		//when
		stud.setEmail("anujGupta@gmail.com");
		stud.setLastName("Gupta");
		Student updatedStudent = studRepo.save(stud);
		//then
		assertThat(updatedStudent.getEmail()).isEqualTo("anujGupta@gmail.com");
		assertThat(updatedStudent.getLastName()).isEqualTo("Gupta");
	}
	@Test
	public void getStudentByIdTest() {
		
		studRepo.save(stud);
		assertThat(studRepo.findById(stud.getId())).isNotNull();
	}
	@Test
	public void getAllStudentsTest() {
		//given
		Student stud1 = new Student();
		stud.setFirstName("Alok");
		stud.setLastName("Bihari");
		stud.setEmail("Alok@gmail.com");
		//when
		studRepo.save(stud);
		studRepo.save(stud1);
		List<Student> studentsList = studRepo.findAll();
		//then
		assertThat(studentsList).isNotNull();
		assertThat(studentsList.size()).isEqualTo(2);
	}
	@Test
	public void DeleteStudentTest() {
		//given
		
		studRepo.save(stud);
		//when
		studRepo.delete(stud);
		//then
		Optional<Student> deletedStudent = studRepo.findById(stud.getId());
		
		assertThat(deletedStudent).isEmpty();
	}
}
