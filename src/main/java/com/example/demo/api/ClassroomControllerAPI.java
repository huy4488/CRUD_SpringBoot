package com.example.demo.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.service.ClassroomService;

@RestController
@RequestMapping("/api/v1/classroom")
@CrossOrigin("*")
public class ClassroomControllerAPI {
	@Autowired
	private ClassroomService classroomService;

	@GetMapping("/")
	public ResponseEntity<List<ClassroomResponseDTO>> listAll() {
		try {
			return new ResponseEntity<>(classroomService.getAllClassroom(), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClassroomResponseDTO> getStudentById(@PathVariable int id) {
		try {
			return new ResponseEntity<>(classroomService.getClassroomById(id), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
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
