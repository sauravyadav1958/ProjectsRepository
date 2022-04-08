package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.dao.AlienRepo;
import com.example.demo.model.Alien;


@Controller
public class AlienController {
	
	@Autowired
	AlienRepo repo;
	
	
	@RequestMapping("/")
	public String home() {
		return "home.jsp";
	}
	
//	@RequestMapping("/addAlien")
//	public String addAlien(Alien alien) {
//		
//		// for saving details in alien table
//		repo.save(alien);
//		return "home.jsp";
//	}
	
	// adding from postman
	@PostMapping("/alien")
	@ResponseBody
	//@RequestBodey will help while sending data in raw format
	public Alien alien(@RequestBody Alien alien) {
		
		// for saving details in alien table
		repo.save(alien);
		return alien;
	}
	
	
	@PutMapping("/alien")
	@ResponseBody
	//@RequestBodey will help while sending data in raw format
	public Alien updateOrCreate(@RequestBody Alien alien) {
		
		// for saving details in alien table
		repo.save(alien);
		return alien;
	}
	
	
	@DeleteMapping("/alien/{aid}")
	@ResponseBody
	public String delete(@PathVariable int aid) {
		
		Alien a = repo.getOne(aid);
		repo.delete(a);
		return "deleted";
	}
	
	
	
	//REST apis
	@RequestMapping(path="/aliens")
	@ResponseBody
	public List<Alien> getAliens() {
		
		return repo.findAll();
	}
	
	@RequestMapping("/alien/{aid}")
	@ResponseBody
	public Optional<Alien> getAlienByAid(@PathVariable int aid) {
		
		return repo.findById(aid);
	}
	
	
	
	@RequestMapping("/getAlien")
	public ModelAndView getAlien(@RequestParam int aid) {
		
		ModelAndView mv = new ModelAndView("showAlien.jsp");
		// for getting details from alien table
		Alien alien = repo.findById(aid).orElse(new Alien());
		// adding data
		mv.addObject(alien);
		
		System.out.println(repo.findByAname("saurav"));
		System.out.println(repo.findByAidGreaterThan(101));
		// manually defined in Alien repo
		System.out.println(repo.findByAnameSortedByAid("mukesh"));
		return mv;
	}
}
