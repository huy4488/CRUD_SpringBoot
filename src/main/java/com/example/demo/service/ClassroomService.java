package com.example.demo.service;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;

import java.util.List;

public interface ClassroomService {
    List<ClassroomResponseDTO> getAllClassroom();

    ClassroomResponseDTO getClassroomById(int id);

    void addNewClassroom(ClassroomDTO classroom);

    boolean updateClassroom(ClassroomDTO classroom, int id);

    void deleteClassroom(int id);
}
