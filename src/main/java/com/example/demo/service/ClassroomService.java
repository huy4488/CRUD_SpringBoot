package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.entity.Student;

public interface ClassroomService {
	List<ClassroomResponseDTO> getAllClassroom();
	ClassroomResponseDTO getClassroomById(int id);
	void addNewClassroom(ClassroomDTO classroom);
	boolean updateClassroom(ClassroomDTO classroom, int id);
	void deleteClassroom(int id);
}
