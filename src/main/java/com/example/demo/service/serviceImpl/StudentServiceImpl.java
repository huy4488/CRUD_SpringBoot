package com.example.demo.service.serviceImpl;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.entity.Student;
import com.example.demo.repository.Classroomrepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentSevice {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Classroomrepository classroomrepository;

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        List<Student> list = studentRepository.findAll();
        List<StudentResponseDTO> listResult = new ArrayList<StudentResponseDTO>();
        for (Student student : list) {
            StudentResponseDTO dto = new StudentResponseDTO(student);
            listResult.add(dto);
        }
        return listResult;
    }

    @Override
    public StudentResponseDTO getStudentById(int id) {
        // TODO Auto-generated method stub
        StudentResponseDTO dto = new StudentResponseDTO(studentRepository.getById(id));
        return dto;
    }

    @Override
    @Transactional
    public boolean addNewStudent(StudentRequestDTO student) {
        // TODO Auto-generated method stub
        Classroom classroom = classroomrepository.getById(student.getClassroom_id());
        if (classroom.equals(null)) {
            return false;
        } else {
            Student newStudent = new Student(student, classroom);
            studentRepository.save(newStudent);
            return true;
        }

    }

    @Override
    public boolean updateStudent(StudentRequestDTO student, int id) {
        // TODO Auto-generated method stub
        if (student == null) {
            return false;
        } else {
            Student s = new Student();
            s = studentRepository.getById(id);
            if (s.equals(null)) {
                return false;
            } else {
                if (student.getClassroom_id() != 0) {
                    Classroom classroom = classroomrepository.getById(student.getClassroom_id());
                    s.setClassroom(classroom);
                }
                if (student.getAddress() != null) {
                    s.setAddress(student.getAddress());
                }
                if (student.getName() != null) {
                    s.setName(student.getName());
                }
                studentRepository.save(s);
                return true;
            }
        }

    }

    @Override
    public void deleteStudent(int id) {
        // TODO Auto-generated method stub
        studentRepository.deleteById(id);

    }

    @Override
    public List<StudentResponseDTO> getStudentByClassroomId(int classroom_id) {
        // TODO Auto-generated method stub
        List<Student> list = studentRepository.FindAllWithDescriptionQuery(classroom_id);
        List<StudentResponseDTO> listResult = new ArrayList<StudentResponseDTO>();
        for (Student student : list) {
            StudentResponseDTO dto = new StudentResponseDTO(student);
            listResult.add(dto);
        }
        return listResult;
    }

}
