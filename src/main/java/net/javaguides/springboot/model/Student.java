package net.javaguides.springboot.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.*;
//@Data //->This annotation will generate getters setters hashcode tostring etc.(Lombok library)
//I am not using lombok library since it was giving me a lot of errors .
//please generate getters and setters manually

@Entity //-> Makes this class a JPA entity
@Table(name="students") //->specifies table name

public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	//@Column(name="first_name",nullable=false)
	private String firstName;
	
	//@Column(name ="last_name")
	private String lastName;
	
	//@Column(name="email")
	private String email;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
