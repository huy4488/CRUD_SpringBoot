package com.example.demo.controller;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.service.StudentSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class StudentController {
    @Autowired
    private StudentSevice studentSevice;
    private String userInfo;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @GetMapping("/all-student")
    public String userInfo(Model model, Principal principal) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();

        System.out.println("User Name: " + userName);

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());
        return "student/list-student";
    }

    @GetMapping("/delete-student/{id}")
    public String toStudent(Model model, Principal principal, @PathVariable int id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());
        boolean checkUpdate = studentSevice.deleteStudent(id);
        if(!checkUpdate){
            model.addAttribute("message", "Update fail");
        } else {
            model.addAttribute("message", "Update success");
            logger.info(loginedUser.getUsername()+" update student with id:"+id+" success");
        }
        return "student/list-student";
    }

    @GetMapping("/to-edit-student/{id}")
    public String toEditStudent(Model model, Principal principal, @PathVariable int id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getStudentById(id));

        return "student/edit-student";
    }

    @PostMapping(value = "/adduser")
    public String adduser(Model model, Principal principal, StudentRequestDTO dto) {

        // Sau khi user login thanh cong se co principal
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        boolean checkAdd = studentSevice.addNewStudent(dto);
        if (!checkAdd){
            model.addAttribute("message","add fail");
            logger.error(loginedUser.getUsername()+" add new student fail");
        } else {
            model.addAttribute("message","add success");
            logger.info(loginedUser.getUsername()+" add new student "+" success");
        }

        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());
        return "student/list-student";
    }

    @PostMapping(value = "/edit-student/{id}")
    public String editStudent(@PathVariable int id, Model model, Principal principal, StudentRequestDTO dto) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        boolean check = studentSevice.updateStudent(dto, id);
        if (check) {


            model.addAttribute("message", true);
            String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("student", studentSevice.getAllStudent());
            model.addAttribute("message", "Update success");
            logger.info(loginedUser.getUsername()+" edit success");

            return "student/list-student";
        } else {
            model.addAttribute("message", "Update fail");
            model.addAttribute("student", studentSevice.getStudentById(id));
            model.addAttribute("error","Update fail");
            logger.error(loginedUser.getUsername()+" edit fail");
            return "student/edit-student";
        }


    }


}
