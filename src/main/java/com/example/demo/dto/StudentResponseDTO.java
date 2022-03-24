package com.example.demo.dto;

import java.io.Serializable;

import com.example.demo.entity.Classroom;
import com.example.demo.entity.Student;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StudentResponseDTO implements Serializable{
	private int id;
	private String name;
	private String address;
//	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
//	private Classroom classroom;
	private int classroomId;
	private String classroomName;
	
	public StudentResponseDTO(String name, String address) {
		super();
		this.name = name;
		//this.classroom_id = classroom_id;
		this.address = address;
	}
	public StudentResponseDTO(Student student) {
		this.id = student.getStudent_id();
		this.name = student.getName();;
		this.address = student.getAddress();
		this.classroomId = student.getClassroom().getClassroom_id();
		this.classroomName = student.getClassroom().getName();
	}
	
	
	
	public StudentResponseDTO(String name, String address, int classroomId, String classroomName) {
		super();
		this.name = name;
		this.address = address;
		this.classroomId = classroomId;
		this.classroomName = classroomName;
	}
	
	public StudentResponseDTO(int id, String name, String address, int classroomId, String classroomName) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.classroomId = classroomId;
		this.classroomName = classroomName;
	}
	public StudentResponseDTO() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public int getClassroomId() {
		return classroomId;
	}
	public void setClassroomId(int classroomId) {
		this.classroomId = classroomId;
	}
	public String getClassroomName() {
		return classroomName;
	}
	public void setClassroomName(String classroomName) {
		this.classroomName = classroomName;
	}
	
}
