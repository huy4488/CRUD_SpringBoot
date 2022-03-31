package com.example.demo.service;

import com.example.demo.dto.ClassroomResponseDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
public class ClassroomServiceTest {
    @InjectMocks
    //@Qualifier("ClassroomServiceImpl")
    ClassroomService classroomService;

    @Test
    public void getAllClassroom_success(){
        List<ClassroomResponseDTO> dtoList = new ArrayList<ClassroomResponseDTO>();
        dtoList = classroomService.getAllClassroom() ;
        assertTrue(dtoList.isEmpty());
    }
}
