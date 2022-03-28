package com.example.demo.service;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;

import java.util.List;


public interface StudentSevice {
    List<StudentResponseDTO> getAllStudent();

    StudentResponseDTO getStudentById(int id);

    boolean addNewStudent(StudentRequestDTO student);

    boolean updateStudent(StudentRequestDTO student, int id);

    void deleteStudent(int id);

    List<StudentResponseDTO> getStudentByClassroomId(int classroom_id);
}
