package com.example.demo.api;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.dto.StudentResponseDTO;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.List;

@Controller
@RequestMapping("/api/v1/student")
@CrossOrigin("*")
public class StudentControllerAPI {
    @Autowired
    private StudentSevice studentSevice;
    private static final Logger logger = Logger.getLogger(StudentControllerAPI.class.getName());

    @GetMapping("/")
    public ResponseEntity<List<StudentResponseDTO>> listAll() {

        logger.info("get all student at "+ Calendar.getInstance());
        return new ResponseEntity<>(studentSevice.getAllStudent(), HttpStatus.OK);
        //ModelAndView andView = new ModelAndView("show-student");
        //andView.addObject("list", studentSevice.getAllStudent());
        //return andView;
        // model.addAttribute("student",studentSevice.getAllStudent());
        //return "student/all-student";

    }

    @GetMapping("/students-by-classroom/{id}")
    public ResponseEntity<List<StudentResponseDTO>> listbyClassroom(@Validated @PathVariable int id) {
        try {
            return new ResponseEntity<>(studentSevice.getStudentByClassroomId(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable int id) {
        try {
            return new ResponseEntity<>(studentSevice.getStudentById(id), HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exceptionL
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/add-student")
    public ResponseEntity addStudent(@RequestBody StudentRequestDTO newStudent) {
        try {
            boolean IsSuccess = studentSevice.addNewStudent(newStudent);
            if (IsSuccess) {
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@RequestBody StudentRequestDTO student, @PathVariable(value = "id") int id) {
        try {
            boolean IsSuccess = studentSevice.updateStudent(student, id);
            if (!IsSuccess) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Student>(HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete-student/{id}")
    public ResponseEntity deleteStudent(@PathVariable(value = "id") int id) {
        try {
            StudentResponseDTO student = studentSevice.getStudentById(id);
            if (student.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                studentSevice.deleteStudent(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

}
