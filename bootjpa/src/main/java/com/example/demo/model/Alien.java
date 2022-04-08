package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

// Specifies that the class is an entity(Table). 
@Entity
public class Alien {
	
	// Specifies that the class is an entity. 
	@Id
	private int aid;
	private String aname;
	
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
	
	@Override
	public String toString() {
		return "Alien [aid=" + aid + ", aname=" + aname + "]";
	}
	
	
}
