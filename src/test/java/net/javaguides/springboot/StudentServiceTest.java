package net.javaguides.springboot;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
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
		stud.setId(0);
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
		assertThat(std1).isEqualTo(stud);
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
	public void getStudentByInvalidIdTest() {
		given(studRepo.findById(2L)).willReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class,
				()-> studService.getStudentById(2L));
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
	@Test
	public void getAllStudentsNoStudentsPresentTest(){
		given(studRepo.findAll()).willReturn(new ArrayList<>());
		List<Student> studList = studService.getAllStudents();
		assertThat(studList).isEmpty();
	}
	@Test
	public void deleteStudentValidIdTest() {
		//given
		given(studRepo.findById(0L)).willReturn(Optional.of(stud));
		willDoNothing().given(studRepo).delete(stud);
		//when
		studService.DeleteStudent(stud.getId());
		verify(studRepo,times(1)).findById(0L);
		//then
		verify(studRepo,times(1)).delete(stud);
	}
	@Test
	public void deleteStudentInValidIdTest() {
		given(studRepo.findById(1L)).willReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class,()->studService.DeleteStudent(1L));
		verify(studRepo,times(1)).findById(1L);
		verify(studRepo,times(0)).delete(stud);
	}
	
}
