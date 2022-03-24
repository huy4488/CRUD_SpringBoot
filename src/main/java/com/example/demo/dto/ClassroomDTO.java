package com.example.demo.dto;

import com.example.demo.entity.Classroom;

public class ClassroomDTO {
	private String name;
	private boolean is_availible;
	public ClassroomDTO() {
		// TODO Auto-generated constructor stub
	}
	public ClassroomDTO(Classroom classroom) {
		// TODO Auto-generated constructor stub
		this.name = classroom.getName();
		this.is_availible = classroom.isIs_availible();			
	}
	public ClassroomDTO(String name, boolean isAvailible) {
		super();
		this.name = name;
		this.is_availible = isAvailible;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIs_availible() {
		return is_availible;
	}
	public void setIs_availible(boolean is_availible) {
		this.is_availible = is_availible;
	}
	
	
	
}
