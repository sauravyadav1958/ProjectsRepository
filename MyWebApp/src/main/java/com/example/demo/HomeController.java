package com.example.demo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//@Controller annotation indicates that a particular class serves the role of a controller.
@Controller
public class HomeController {
	//It is used to map web requests onto specific handler classes and/or handler methods.
	@RequestMapping("home")
	public ModelAndView home(Alien alien) {
		// Model = data, View = webpage
		ModelAndView mv = new ModelAndView();
		// adding data in model
		mv.addObject("AlienObjParam", alien);
		// setting webpge in view
		mv.setViewName("home");
		// returning ModelAndView Object
		return mv;
	}

}
