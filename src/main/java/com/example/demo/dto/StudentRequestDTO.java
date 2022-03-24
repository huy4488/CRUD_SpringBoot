package com.example.demo.dto;

import java.io.Serializable;

public class StudentRequestDTO implements Serializable{
	private String name;
	private String address;
	private int classroom_id;	

	public StudentRequestDTO() {
		// TODO Auto-generated constructor stub
	}

	public StudentRequestDTO(String name, String address, int classroom_id) {
		super();
		this.name = name;
		this.address = address;
		this.classroom_id = classroom_id;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(int classroom_id) {
		this.classroom_id = classroom_id;
	}
	
	
}
