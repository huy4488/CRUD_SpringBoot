package com.example.demo.controller;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.service.StudentSevice;
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
        studentSevice.deleteStudent(id);
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
        studentSevice.addNewStudent(dto);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());

        return "student/list-student";
    }

    @PostMapping(value = "/edit-student/{id}")
    public String editStudent(@PathVariable int id, Model model, Principal principal, StudentRequestDTO dto) {

        // Sau khi user login thanh cong se co principal
//        String userName = principal.getName();
//
//
//
//        System.out.println("User Name: " + userName);
        boolean check = studentSevice.updateStudent(dto, id);
        if (check) {

            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("message", true);
            String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("student", studentSevice.getAllStudent());

            return "student/list-student";
        } else {
            model.addAttribute("message", "Update fail");
            model.addAttribute("student", studentSevice.getStudentById(id));
            return "student/edit-student";
        }


    }


}
