package net.javaguides.springboot;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import java.util.Optional;

import net.javaguides.springboot.model.Student;
import net.javaguides.springboot.repository.StudentRepository;
import net.javaguides.springboot.service.impl.StudentServiceImpl;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {
	
	@Mock
	private StudentRepository studRepo;
	@InjectMocks
	private StudentServiceImpl studService;
	private Student stud;
	
	@BeforeEach
	public void setup() {
		stud = new Student();
		stud.setFirstName("Anuj");
		stud.setLastName("Khare");
		stud.setEmail("anuj@gmail.com");
	}
	@Test
	public void saveStudentTest() {
		//given
		given(studRepo.save(stud)).willReturn(stud);
		//when
		Student std1 = studService.saveStudent(stud);
		//then
		assertThat(std1).isNotNull();
	}
	@Test
	public void getStudentByIdTest() {
		//given
		
		//when
		given(studRepo.findById(stud.getId())).willReturn(Optional.of(stud));
		//then
		Student savedStudent = studService.getStudentById(stud.getId());
		assertThat(savedStudent).isEqualTo(stud);
	}
	@Test 
	public void getAllStudentsTest(){
		//given
		Student stud1 = new Student();
		stud1.setFirstName("Hanuma");
		stud1.setLastName("Vihari");
		stud1.setEmail("Hanuma@gmail.com");
		
		given(studRepo.findAll()).willReturn(List.of(stud,stud1));
		//when
		List<Student> studList = studService.getAllStudents();
		//then
		assertThat(studList).isNotNull();
		assertThat(studList.size()).isEqualTo(2);
	}
	
}
