package com.example.demo;

import org.springframework.stereotype.Component;
//By default @component creates object with same name as class but first letter is small
// Here we are making sure that the object will be created with name lap1
@Component("lap1")
public class Laptop {
	private int lid;
	private String brand;
	
	public int getLid() {
		return lid;
	}
	public void setLid(int lid) {
		this.lid = lid;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	@Override
	public String toString() {
		return "Laptop [lid=" + lid + ", brand=" + brand + "]";
	}
	
	public void compile() {
		System.out.println("compiling..");
	}
}
