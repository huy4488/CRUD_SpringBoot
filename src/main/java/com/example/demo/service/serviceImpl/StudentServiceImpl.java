package com.example.demo.service.serviceImpl;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.entity.Student;
import com.example.demo.exceptions.SaveDataSqlException;
import com.example.demo.repository.Classroomrepository;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentSevice {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private Classroomrepository classroomrepository;
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Override
    public List<StudentResponseDTO> getAllStudent() {
        List<Student> list = studentRepository.findAll();
        List<StudentResponseDTO> listResult = new ArrayList<StudentResponseDTO>();
        if (list.isEmpty()) {
            logger.warn("Don't found any student in database");
        } else {
            for (Student student : list) {
                StudentResponseDTO dto = new StudentResponseDTO(student);
                listResult.add(dto);
            }
        }
        return listResult;
    }

    @Override
    public StudentResponseDTO getStudentById(int id) {
        // TODO Auto-generated method stub
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            logger.warn("Not found student with id:" + id);
            return null;
        } else {
            StudentResponseDTO dto = new StudentResponseDTO(optionalStudent.get());
            return dto;
        }

    }

    @Override
    @Transactional
    public boolean addNewStudent(StudentRequestDTO student) {
        // TODO Auto-generated method stub
        try {
            Optional<Classroom> classroom = classroomrepository.findById(student.getClassroom_id());
            if (classroom.isEmpty()) {
                logger.warn("Not found classroom with id:" + student.getClassroom_id());
                return false;
            } else {
                Student newStudent = new Student(student, classroom.get());
                studentRepository.save(newStudent);
                return true;
            }
        } catch (Exception e) {
            throw new SaveDataSqlException("Not save new student because:" + e.getMessage());
        }
    }

    @Override
    public boolean updateStudent(StudentRequestDTO student, int id) {
        // TODO Auto-generated method stub
        try {
            if (student == null) {
                return false;
            } else {
                Optional<Student> optionalStudent = studentRepository.findById(id);
                if (optionalStudent.equals(null)) {
                    return false;
                } else {
                    Student updateStudent = new Student();
                    updateStudent = optionalStudent.get();
                    if (student.getClassroom_id() != 0) {
                        Optional<Classroom> classroom = classroomrepository.findById(student.getClassroom_id());
                        updateStudent.setClassroom(classroom.get());
                    }
                    if (student.getAddress() != null) {
                        updateStudent.setAddress(student.getAddress());
                    }
                    if (student.getName() != null) {
                        updateStudent.setName(student.getName());
                    }
                    studentRepository.save(updateStudent);
                    return true;
                }
            }
        } catch (Exception e) {
            throw new SaveDataSqlException("Not update student because:" + e.getMessage());
        }


    }

    @Override
    public boolean deleteStudent(int id) {

        // TODO Auto-generated method stub
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            logger.warn("not found student with id:" + id);
            return  false;
        } else {
            studentRepository.deleteById(id);
            return true;
        }

    }

    @Override
    public List<StudentResponseDTO> getStudentByClassroomId(int classroom_id) {
        // TODO Auto-generated method stub
        List<Student> list = studentRepository.FindAllWithDescriptionQuery(classroom_id);
        List<StudentResponseDTO> listResult = new ArrayList<StudentResponseDTO>();
        if(list.isEmpty()){
            logger.warn("Not found student in classrrom with id:"+classroom_id);
            return null;
        } else {
            for (Student student : list) {
                StudentResponseDTO dto = new StudentResponseDTO(student);
                listResult.add(dto);
            }
            return listResult;
        }

    }

}
