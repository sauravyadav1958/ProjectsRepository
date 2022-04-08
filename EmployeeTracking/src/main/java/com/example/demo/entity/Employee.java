package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "EMPLOYEE")
public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

//	@Column(nullable = false, length = 20)
//	@NotEmpty // here blank spaces are considered.
	@NotNull(message="Name can't be null")
	@NotBlank(message="Name can't be blank")
	@Size(min = 3, max = 20, message="Name length must be between 3 and 20")
	@Pattern(regexp="^[a-zA-Z]*$", message = "Must not contain numbers/special character")
	private String name;



	@NotNull(message="Address can't be null")
	@NotBlank(message="Address can't be blank")
	@Size(min = 3, max = 45, message="Address length must be between 3 and 40")
	private String address;


	@Column(unique = true)
	@Email(message = "Email format is not correct")
	@NotNull(message="Email can't be null")
	@NotBlank(message="Email can't be blank")
	@Size(min = 3, max = 45, message="Email length must be between 3 and 40")
	private String email;



	@NotNull(message="phno can't be null")
	@NotBlank(message="phno can't be blank")
	@Size(min = 10, max = 10, message="phno length must be 10")
//	@Pattern(regexp="^[0-9]*$", message="phno should contain only digits")
	@Digits(integer = 10, fraction = 0, message="phno should contain only digits")
	private String phno;



	@NotNull(message="Salary can't be null")
//	@NotBlank(message="Salary can't be blank") // can't be applied for int
	@Min(value = 1000, message="Salary length must be between 4 and 9")
	@Max(value = 999999999, message="Salary length must be between 4 and 9")
//	@Pattern(regexp="^[0-9]*$", message="Salary should contain only digits") // only for Strings
	@Digits(integer = 9, fraction = 0, message="No decimal values")
	private int salary;

	
	
}
