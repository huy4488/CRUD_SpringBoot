package com.example.demo.api;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.service.ClassroomService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;
import java.util.List;

@RestController
@RequestMapping("/api/v1/classroom")
@CrossOrigin("*")
public class ClassroomControllerAPI {
    @Autowired
    private ClassroomService classroomService;
    private static final Logger logger = Logger.getLogger(ClassroomControllerAPI.class.getName());
    @GetMapping("/")
    public ResponseEntity<List<ClassroomResponseDTO>> listAll() {
        try {
            logger.info("get element at "+ Calendar.getInstance());
            return new ResponseEntity<>(classroomService.getAllClassroom(), HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomResponseDTO> getStudentById(@PathVariable int id) {

        return new ResponseEntity<>(classroomService.getClassroomById(id), HttpStatus.OK);
    }

    //
    @PostMapping("/add-classroom")
    public ResponseEntity addClassroom(@RequestBody ClassroomDTO newClassroom) {
        try {
            classroomService.addNewClassroom(newClassroom);
            return new ResponseEntity<>(null, HttpStatus.OK);
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    //
    @PutMapping("/{id}")
    public ResponseEntity<Classroom> update(@RequestBody ClassroomDTO classroom, @PathVariable(value = "id") int id) {
        try {
            var check = classroomService.updateClassroom(classroom, id);
            if (!check) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                return new ResponseEntity<Classroom>(HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/delete-classroom/{id}")
    public ResponseEntity<ClassroomResponseDTO> delete(@PathVariable(value = "id") int id) {
        try {
            ClassroomResponseDTO dto = classroomService.getClassroomById(id);
            if (dto.equals(null)) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            } else {
                classroomService.deleteClassroom(id);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            // TODO: handle exception
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }

}
