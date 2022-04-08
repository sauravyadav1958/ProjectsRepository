package com.example.demo;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

// This will create bean automatically for Alien 
// when programmed is executed. It will create only one Bean.
// If we try to access another bean,
// It will not work as another bean dosen't exist.
@Component
public class Alien {
	private int aid;
	private String aname;
	private String tech;
	// laptop object is there in container but can't use context.getBean here to access it
	// Hence Autowired is used, it will search for Laptop type object in container(byType)
	@Autowired
	// Tells spring to search for Bean byName : lap1 
	@Qualifier("lap1")
	private Laptop laptop;
	
	public Alien(){
		System.out.println("Alien Object getting created only once");
	}
	
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public String getTech() {
		return tech;
	}
	public void setTech(String tech) {
		this.tech = tech;
	}
	
	public void show() {
		System.out.println("Alien dummy method");
		laptop.compile();
	}
}
