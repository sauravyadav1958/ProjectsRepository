package com.example.demo.service;

import java.rmi.ServerException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Employee;
import com.example.demo.repository.EmpRepo;
import org.webjars.NotFoundException;

@Service
public class EmpService {
	
	@Autowired
	private EmpRepo repo;
	
	public ResponseEntity<Employee> addEmp(Employee employee) {


		if(employee == null){
			throw new IllegalStateException("All the Fields are empty");
		}

		repo.save(employee);

		return new ResponseEntity<>(employee, HttpStatus.CREATED);
	}
	
	public ResponseEntity<List<Employee>> getAllEmp(){


		return new ResponseEntity<>(repo.findAll(), HttpStatus.OK);
	}
	
	public ResponseEntity<Employee> getEmpById(int id) {
		
		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent()) {
			return new ResponseEntity<>(employee.get(), HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

	}
	
	public void deleteEmp(int id) {

		Optional<Employee> employee = repo.findById(id);
		if(employee.isPresent()) {
			repo.deleteById(id);
		}else{
			throw new NotFoundException("Data Not Found !!");
		}

	}

	public void deleteAll() {
		 repo.deleteAll();

	}

	public ResponseEntity<Employee> edit(int id, Employee employee) {
		Optional<Employee> e = repo.findById(id);

		if(e.isPresent()) {
			employee.setId(id);// for setting the same above id automatically as this can't be edited by user
			addEmp(employee);
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			throw new NotFoundException("Data dosen't exist !!");
		}


	}
}
