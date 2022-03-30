package com.example.demo.controller;

import com.example.demo.service.ClassroomService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import eu.ubicon.webapp.test.WebappTestEnvironment;

@AutoConfigureMockMvc
//@DataJpaTest
@WebMvcTest(ClassroomController.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT

)
@RunWith(SpringRunner.class)
public class ClassroomControllerTest extends WebappTestEnvironmen{
    public static class MockSecurityContext implements SecurityContext {



        private Authentication authentication;

        public MockSecurityContext(Authentication authentication) {
            this.authentication = authentication;
        }

        @Override
        public Authentication getAuthentication() {
            return this.authentication;
        }

        @Override
        public void setAuthentication(Authentication authentication) {
            this.authentication = authentication;
        }
    }
    @Autowired
    private WebApplicationContext wac;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ClassroomService service;
    @Before
    public void setUp() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    @WithMockUser(roles = "ROLE_ADMIN")
    public void givenEmployees_whenGetEmployees_thenReturnJsonArray()
            throws Exception {
//        UsernamePasswordAuthenticationToken principal =
//                this.getPrincipal("dbadmin1");
        //SecurityContextHolder.getContext().setAuthentication(principal);
        MvcResult result = this.mockMvc.perform(get("/admin")).andReturn();
        assertTrue(result.equals("classroom/adminPage"));


    }
}
