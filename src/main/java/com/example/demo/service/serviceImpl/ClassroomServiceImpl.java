package com.example.demo.service.serviceImpl;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.repository.Classroomrepository;
import com.example.demo.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
//        Classroom classroom = classroomRepository.getById(id);
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);

        if(classroomOptional.isEmpty()){

            throw new ResourceNotFoundException("Not found the classroom with id:"+id);
        }
        ClassroomResponseDTO dto = new ClassroomResponseDTO(classroomOptional.get());

        return dto;
    }

    @Override
    public void addNewClassroom(ClassroomDTO classroom) {
        // TODO Auto-generated method stub
        try {
            Classroom newClassroom = new Classroom(classroom);
            classroomRepository.save(newClassroom);
        } catch (Exception e){
//            if (e.getMessage().contains("foreign key")){
//                throw new ResourceNotFoundException("foreign key not found");
//            }
        }

    }

    @Override
    public boolean updateClassroom(ClassroomDTO classroom, int id) {
        // TODO Auto-generated method stub
        Classroom oldClassroom = new Classroom();
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);
        if (classroomOptional.equals(null)) {
            throw new ResourceNotFoundException("Not found with classroom id = "+id);
        } else {
            oldClassroom = classroomOptional.get();
            oldClassroom.setIs_availible(classroom.isIs_availible());
            oldClassroom.setName(classroom.getName());
            classroomRepository.save(oldClassroom);
            return true;
        }
    }

    @Override
    public void deleteClassroom(int id) {
        // TODO Auto-generated method stub
        classroomRepository.deleteById(id);
    }


}
