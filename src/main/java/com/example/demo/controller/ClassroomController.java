package com.example.demo.controller;

import com.example.demo.dto.ClassroomDTO;
import com.example.demo.service.ClassroomService;
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
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;
    private static final Logger logger = LoggerFactory.getLogger(ClassroomController.class);

    @GetMapping("/admin")
    public String adminPage(Model model, Principal principal) {

        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("classrooms", classroomService.getAllClassroom());
        //logger.info(loginedUser.getUsername() +"login");

        return "classroom/adminPage";
    }

    @PostMapping(value = "/add-classroom")
    public String adduser(Model model, Principal principal, ClassroomDTO dto) {

        // Sau khi user login thanh cong se co principal

        classroomService.addNewClassroom(dto);
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("classrooms", classroomService.getAllClassroom());
        logger.info(loginedUser.getUsername()+" add new classroom successful");

        return "classroom/adminPage";
    }

    @PostMapping(value = "/edit-classroom/{id}")
    public String editStudent(@PathVariable int id, Model model, Principal principal, ClassroomDTO dto) {

        // Sau khi user login thanh cong se co principal
//        String userName = principal.getName();

        boolean check = classroomService.updateClassroom(dto, id);
        User loginedUser = null;
        if (check) {

            loginedUser = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("message", true);
            String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("classrooms", classroomService.getAllClassroom());
            logger.info(loginedUser.getUsername() + " edit classroom with id: " + id + " successful");

            return "classroom/adminPage";
        } else {
            model.addAttribute("message", "Update fail");
            model.addAttribute("classroom", classroomService.getClassroomById(id));
            model.addAttribute("error","update fail");
            logger.error(loginedUser.getUsername()+ "edit classroom fail with classroom id:"+id);
            return "classroom/edit-classroom";
        }
    }

    @GetMapping("/delete-classroom/{id}")
    public String toStudent(Model model, Principal principal, @PathVariable int id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();
        classroomService.deleteClassroom(id);
        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("classrooms", classroomService.getAllClassroom());

        return "classroom/adminPage";
    }

    @GetMapping("/edit-classroom/{id}")
    public String toEditStudent(Model model, Principal principal, @PathVariable int id) {
        User loginedUser = (User) ((Authentication) principal).getPrincipal();

        String userInfo = com.example.demo.utils.WebUtils.toString(loginedUser);
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("classroom", classroomService.getClassroomById(id));
        model.addAttribute("classroom_id", id);

        return "classroom/edit-classroom";
    }
}
