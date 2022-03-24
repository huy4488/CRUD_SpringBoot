package com.example.demo.service.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.repository.Classroomrepository;
import com.example.demo.service.ClassroomService;

@Service
public class ClassroomServiceImpl implements ClassroomService {
	@Autowired 
	private Classroomrepository classroomRepository;

	@Override
	public List<ClassroomResponseDTO> getAllClassroom() {
		// TODO Auto-generated method stub
		//List<Classroom> list = classroomRepository.findAll(Sort.by(Sort.Direction.ASC, "classroom_id"));		
		List<Classroom> list = classroomRepository.findAll();
		List<ClassroomResponseDTO> listResult = new ArrayList<ClassroomResponseDTO>();
		for (Classroom classroom : list) {
			ClassroomResponseDTO dto = new ClassroomResponseDTO(classroom);
			listResult.add(dto);
		}		

		return listResult;
	}

	@Override
	public ClassroomResponseDTO getClassroomById(int id) {
		// TODO Auto-generated method stub
		ClassroomResponseDTO dto = new ClassroomResponseDTO(classroomRepository.getById(id));
		return dto;
	}

	@Override
	public void addNewClassroom(ClassroomDTO classroom) {
		// TODO Auto-generated method stub
		Classroom newClassroom = new Classroom(classroom);
		classroomRepository.save(newClassroom);
	}

	@Override
	public boolean updateClassroom(ClassroomDTO classroom, int id) {
		// TODO Auto-generated method stub
		Classroom s = new Classroom();
		s = classroomRepository.findById(id).get();
		if(s.equals(null)) {
			return false;
		} else {
			s.setIs_availible(classroom.isIs_availible());		
			s.setName(classroom.getName());
			classroomRepository.save(s);
			return true;
		}
	}

	@Override
	public void deleteClassroom(int id) {
		// TODO Auto-generated method stub
		classroomRepository.deleteById(id);
	}


	
	
}
