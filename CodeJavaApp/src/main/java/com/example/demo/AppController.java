package com.example.demo;

import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
 
@Controller
public class AppController {
 
    @Autowired
    private UserRepository userRepo;
    
    @Autowired
    private CustomUserDetailsService service;
     
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
    
    @GetMapping("/login")
    public String login(Model model) {
    	model.addAttribute("user", new User());
        return "login_form";
    }
    
    @PostMapping("/login")
    public String login(User user, Model model) {
    	User oauthUser = service.login(user.getEmail(), user.getPassword());
        
    	System.out.print(oauthUser);
    	
    	if(Objects.nonNull(oauthUser) )
        {
    		List<User> listUsers = userRepo.findAll();
            model.addAttribute("listUsers", listUsers);
        return "users";
        
        } else {
        return "redirect:/login";
        }
        
    }

    @GetMapping("/users")
    public String listUsers(Model model) {
    	List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
         
        return "users";
    }
    
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
}
