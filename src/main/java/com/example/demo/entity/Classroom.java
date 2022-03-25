package com.example.demo.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;

@Entity
@Table(name = "classroom")
public class Classroom implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@TableGenerator(name = "Classroom_Gen", table = "ID_GEN", initialValue = 12000)

	@Id
	@Column(name = "classroom_id")
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Classroom_Gen")
	private int classroom_id;

	@Column(name = "name")

	private String name;

	@Column(name = "is_availible")
	private boolean is_availible;

	@OneToMany(mappedBy = "classroom", cascade = CascadeType.ALL)
	private List<Student> students;

	public Classroom(int classroom_id, String name, boolean is_availible) {
		super();
		this.classroom_id = classroom_id;
		this.name = name;
		this.is_availible = is_availible;
	}

	public Classroom(ClassroomDTO dto) {
		super();
		this.classroom_id = classroom_id;
		this.name = dto.getName();
		this.is_availible = dto.isIs_availible();
	}

	public Classroom(ClassroomResponseDTO dto) {
		super();
		this.classroom_id = dto.getClassroom_id();
		this.name = dto.getName();
		this.is_availible = dto.isAvailable();
	}

	public Classroom() {
		super();
	}

	public int getClassroom_id() {
		return classroom_id;
	}

	public void setClassroom_id(int classroom_id) {
		this.classroom_id = classroom_id;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Classroom(String name, boolean is_availible) {
		super();
		this.name = name;
		this.is_availible = is_availible;
	}

//	@OneToMany(fetch = FetchType.LAZY)	
//	@JoinColumn(name = "classroom_id")
//	Set<Student> list = new HashSet<>();

}
