package com.example.demo.controller;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
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
	public ResponseEntity<List<Employee>> listUsers() {

		return empservice.getAllEmp();
//		model.addAttribute("emp", emp);

//		return emp;
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmpById(@PathVariable int id)
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



	@PostMapping("/registerEmp")
	public ResponseEntity<Employee> empRegister(@RequestBody Employee employee) {

//		System.out.println(employee);
		return empservice.addEmp(employee);

	}
    	
    	@GetMapping("/registerEmp")
    	public String registerEmp(Model model) {
    		
    		return "redirect:/employees";
    	}
    	
    	@PutMapping("/edit/{id}")
    	public ResponseEntity<Employee> edit(@PathVariable int id, @RequestBody Employee employee) {
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
	public void deleteEmp(@PathVariable int id) {


		empservice.deleteEmp(id);
//		session.setAttribute("msg", "Employee data deleted Successfully..");


	}


	@DeleteMapping("/deleteAll")
	public void deleteAll() {

		 empservice.deleteAll();
//		session.setAttribute("msg", "Employee data deleted Successfully..");


	}


    	
    
}
