package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		// Here run method is of type ConfigurableApplicationContext.
		// Hence, we are creating container which can store multiple beans/objects.
		ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
		
		// here new bean of type Alien will get accessed from container.
		Alien a = context.getBean(Alien.class);
		
		a.show();
		
	}

}
