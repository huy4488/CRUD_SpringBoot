package com.example.demo.service.serviceImpl;

import com.example.demo.controller.ClassroomController;
import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.entity.Classroom;
import com.example.demo.exceptions.ResourceNotFoundException;
import com.example.demo.exceptions.SaveDataSqlException;
import com.example.demo.repository.Classroomrepository;
import com.example.demo.service.ClassroomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClassroomServiceImpl implements ClassroomService {
    @Autowired
    private Classroomrepository classroomRepository;
    private static final Logger logger = LoggerFactory.getLogger(ClassroomServiceImpl.class);

    @Override
    public List<ClassroomResponseDTO> getAllClassroom() {
        // TODO Auto-generated method stub
        //List<Classroom> list = classroomRepository.findAll(Sort.by(Sort.Direction.ASC, "classroom_id"));
        List<Classroom> list = classroomRepository.findAll();
        List<ClassroomResponseDTO> listResult = new ArrayList<ClassroomResponseDTO>();
        if(list.isEmpty()){
            logger.info("Don't have any classroom in database");
        } else{
            for (Classroom classroom : list) {
                ClassroomResponseDTO dto = new ClassroomResponseDTO(classroom);
                listResult.add(dto);
            }
        }


        return listResult;
    }

    @Override
    public ClassroomResponseDTO getClassroomById(int id) {
        // TODO Auto-generated method stub
//        Classroom classroom = classroomRepository.getById(id);
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);

        if(classroomOptional.isEmpty()){
            logger.warn("Not found the classroom with id:"+id);
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

            logger.error("Save classroom fail because :"+e.getMessage());
            throw new  SaveDataSqlException("Save classroom data fail");

        }

    }

    @Override
    public boolean updateClassroom(ClassroomDTO classroom, int id) {
        // TODO Auto-generated method stub
        try {
            Classroom oldClassroom = new Classroom();
            Optional<Classroom> classroomOptional = classroomRepository.findById(id);
            if (classroomOptional.equals(null)) {
                logger.warn("Not found the classroom with id:"+id);
                return false;
            } else {
                oldClassroom = classroomOptional.get();
                oldClassroom.setIs_availible(classroom.isIs_availible());
                oldClassroom.setName(classroom.getName());
                classroomRepository.save(oldClassroom);
                return true;
            }
        } catch (Exception e) {
            logger.error("Update classroom fail because :"+e.getMessage());
            throw new SaveDataSqlException("Update classroom fail because :"+e.getMessage(),e);

        }

    }

    @Override
    public void deleteClassroom(int id) {
        // TODO Auto-generated method stub
        Optional<Classroom> classroomOptional = classroomRepository.findById(id);
        if(classroomOptional.isEmpty()){
            logger.warn("Don't find classroom with id:"+id);
        }else {
            classroomRepository.deleteById(id);
        }

    }


}
