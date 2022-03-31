package com.example.demo.controller;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.dto.ClassroomResponseDTO;
import com.example.demo.service.ClassroomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.Mockito.*;

@AutoConfigureMockMvc
//@DataJpaTest
@ExtendWith(MockitoExtension.class)
@WebMvcTest(ClassroomController.class)
@RunWith(SpringRunner.class)
public class ClassroomControllerTest  {

    @Autowired
    private WebApplicationContext wac;
    @InjectMocks
    ClassroomController classroomController;
    @Mock
    ClassroomService classroomService;


    //private MockMvc mockMvc;
    //@MockBean

//    @Before
//    public void setUp() throws Exception {
//        //this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
//    }

    @Test
    @WithMockUser(username = "dbadmin1",password = "123",roles = "ROLE_ADMIN")
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        ClassroomResponseDTO dto = new ClassroomResponseDTO(1,"Alex",true);
        ClassroomResponseDTO dto1 = new ClassroomResponseDTO(2,"Ana",true);
        ClassroomResponseDTO dto2 = new ClassroomResponseDTO(3,"Lokesh",true);
        List<ClassroomResponseDTO> dtoList = new ArrayList<ClassroomResponseDTO>();
        dtoList.add(dto);
        dtoList.add(dto1);
        dtoList.add(dto2);

        when(classroomService.getAllClassroom()).thenReturn(dtoList);

        String result = classroomController.adminPage();

//        UsernamePasswordAuthenticationToken principal =
//                this.getPrincipal("dbadmin1");
        //SecurityContextHolder.getContext().setAuthentication(principal);
//        MvcResult asdas1 = this.mockMvc
//                .perform(get("/admin"))
//                .andReturn();
//                //.andExpect(status().isOk());
//        assertTrue(asdas1.equals("classroom/adminPage"));


    }
}
