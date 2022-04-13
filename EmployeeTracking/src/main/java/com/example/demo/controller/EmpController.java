package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import com.example.demo.entity.responseDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateTimeFormatAnnotationFormatterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entity.Employee;
import com.example.demo.entity.User;
import com.example.demo.repository.EmpRepo;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.EmpService;
import org.webjars.NotFoundException;


@RestController
public class EmpController {
	
	@Autowired
	private UserRepository userRepo;

	@Autowired
	private CustomUserDetailsService service;
	
	@Autowired
	private EmpRepo empRepo;

	@Autowired
	private EmpService empservice;
	
	
	@GetMapping("")
    public String viewHomePage() {
        return "index";
    }
    
    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
    	//takes input
        model.addAttribute("user", new User());

        return "signup_form";
    }
    
    @PostMapping("/process_register")
    public String processRegister(User user) {
    	System.out.println(user);
        userRepo.save(user);

        return "register_success";
    }




    // For reloading the success page again
    @GetMapping("/process_register")
    public String processRegisterGet(User user) {

        return "register_success";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("user", new User());
        return "login_form";
    }
    
    @PostMapping("/postLogin")
    public String login(User user, Model model) {
    	
    	User oauthUser = service.login(user.getEmail(), user.getPassword());
    	

    	System.out.print(oauthUser);
    	
    	if(Objects.nonNull(oauthUser) )
        {
    		
    		return "redirect:/employees";
        
        } else {
        return "redirect:/login";
        }
        
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
    

    	
    	@GetMapping("/addemp")
    	public String addEmpForm() {
    		return "add-emp";
    	}
    	
//    	 @GetMapping("/employees")
//    	 	public String listUsers(Model model) {
//
//    		List<Employee> emp= empservice.getAllEmp();
//     		model.addAttribute("emp", emp);
//
//     		return "employeeList";
//       }

	@GetMapping("/employees")
	public ResponseEntity<?> listUsers() {


		return empservice.getAllEmp();

	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<?> getEmpById(@PathVariable int id)
	{


		return empservice.getEmpById(id);
	}
    	
    	
    	
//    	@PostMapping("/registerEmp")
//    	public String empRegister(Employee e , HttpSession session, Model model) {
//
//    		System.out.println(e);
//
//    		empservice.addEmp(e);
//    		session.setAttribute("msg", "Employee Added Successfully..");
//    		List<Employee> emp= empservice.getAllEmp();
//    		model.addAttribute("emp", emp);
//
//    		return "redirect:/employees";
//
//    	}

//	@Autowired
//	private responseDetails responseDetailsObj;

	responseDetails responseDetailsObj = new responseDetails();

	@PostMapping("/registerEmp")
	public ResponseEntity<?> empRegister(@RequestBody Employee employee) {

		if(employee.getName() == null) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getName().isBlank()){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getName().matches("[a-zA-Z]+")) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Name must not contains numbers/special characters");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getAddress() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Address can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getAddress().isBlank()){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Address can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getEmail() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getEmail().isBlank()){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getEmail().matches("^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("Email format is not correct");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno() == null){
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber can't be null");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno().isBlank()) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber can't be empty");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!employee.getPhno().matches("^(0|[1-9][0-9]*)$")) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber should contains digits only/number shouldn't start from zero");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(employee.getPhno().length() != 10) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("PhoneNumber length should be 10 exactly");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}else if(!(employee.getSalary() >= 0 &&  employee.getSalary() <= 10000000)) {
			responseDetailsObj.setDateAndTime(LocalDateTime.now());
			responseDetailsObj.setResponseCode("400");
			responseDetailsObj.setResponseMessage("salary should be between 0 and 10000000");
			responseDetailsObj.setResponseData(null);
			return new ResponseEntity<>(responseDetailsObj, HttpStatus.BAD_REQUEST);
		}

		return empservice.addEmp(employee);

	}
    	
    	@GetMapping("/registerEmp")
    	public String registerEmp(Model model) {
    		
    		return "redirect:/employees";
    	}
    	
    	@PutMapping("/edit/{id}")
    	public ResponseEntity<?> edit(@PathVariable int id, @RequestBody Employee employee) {
//    		Employee e = empservice.getEmpById(id);

//    		m.addAttribute("emp", e);
    		
    		return empservice.edit(id, employee);
    	}
    	
    	@PostMapping("/update")
    	public String updateEmp(@ModelAttribute Employee e, HttpSession session, Model model) {
    		
    		empservice.addEmp(e);
    		session.setAttribute("msg", "Employee data edited Successfully..");
    		
    		return "redirect:/employees";
    	}
    	
    	@GetMapping("/update")
    	public String update(@ModelAttribute Employee e, HttpSession session, Model model) {

    		return "redirect:/employees";
    	}
    	
//    	@GetMapping("/delete/{id}")
//    	public String deleteEmp(@PathVariable int id, HttpSession session, Model model) {
//
//    		Employee e = empservice.getEmpById(id);
//    		if(e==null) {
//
//    			return "redirect:/employees";
//    		}
//    		empservice.deleteEmp(id);
//    		session.setAttribute("msg", "Employee data deleted Successfully..");
//
//    		return "redirect:/employees";
//    	}


	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteEmp(@PathVariable int id) {


		return empservice.deleteEmp(id);
//		session.setAttribute("msg", "Employee data deleted Successfully..");


	}


	@DeleteMapping("/deleteAll")
	public ResponseEntity<?> deleteAll() {

		 return empservice.deleteAll();
//		session.setAttribute("msg", "Employee data deleted Successfully..");


	}


    	
    
}
