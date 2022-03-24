package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.ForeignKey;

import com.example.demo.dto.StudentRequestDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "student")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")	
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "name")	
	private String name;

	@Column(name = "address")
	private String address;
	
//	@ForeignKey(name = "classroom_id")
//	@Column(name = "classroom_id")
//	private String classroom_id;
	

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	@JoinColumn(name = "classroom_id")	
	private Classroom classroom;	
	
	
	

	public Classroom getClassroom() {
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int student_id, String name, String address) {
		super();
		this.id = student_id;
		this.name = name;
		this.address = address;
	}
	public Student(StudentRequestDTO dto, Classroom classroom) {
		super();
		
		this.name = dto.getName();
		this.address = dto.getAddress();
		this.classroom = classroom;
	}

	public int getStudent_id() {
		return id;
	}

	public void setStudent_id(int student_id) {
		this.id = student_id;
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
}
