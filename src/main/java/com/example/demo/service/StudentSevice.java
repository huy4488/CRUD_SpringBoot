package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;



public interface StudentSevice {
	List<StudentResponseDTO> getAllStudent();
	StudentResponseDTO getStudentById(int id);
	boolean addNewStudent(StudentRequestDTO student);
	boolean updateStudent(StudentRequestDTO student, int id);
	void deleteStudent(int id);
	List<StudentResponseDTO> getStudentByClassroomId(int classroom_id);
}
