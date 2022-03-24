package com.example.demo.controoler;

import com.example.demo.dto.StudentRequestDTO;
import com.example.demo.service.StudentSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    public String toStudent(Model model, Principal principal,@PathVariable int id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());
        studentSevice.deleteStudent(id);
        return "student/list-student";
    }

    @PostMapping(value = "/adduser")
    public String adduser(Model model, Principal principal, StudentRequestDTO dto) {

        // Sau khi user login thanh cong se co principal
        String userName = principal.getName();



        System.out.println("User Name: " + userName);
        studentSevice.addNewStudent(dto);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("student", studentSevice.getAllStudent());

        return "student/list-student";
    }


}
