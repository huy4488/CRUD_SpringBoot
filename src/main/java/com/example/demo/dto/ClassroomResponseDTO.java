package com.example.demo.dto;

import com.example.demo.entity.Classroom;

public class ClassroomResponseDTO {
    private boolean isAvailable;
    private int classroom_id;
    private String name;


    public int getClassroom_id() {
        return classroom_id;
    }

    public void setClassroom_id(int classroom_id) {
        this.classroom_id = classroom_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public ClassroomResponseDTO(int classroom_id, String name, boolean isAvailable) {
        super();
        this.classroom_id = classroom_id;
        this.name = name;
        this.isAvailable = isAvailable;
    }

    public ClassroomResponseDTO(Classroom classroom) {
        super();
        this.classroom_id = classroom.getClassroom_id();
        this.name = classroom.getName();
        this.isAvailable = classroom.isIs_availible();
    }


    public ClassroomResponseDTO() {
        // TODO Auto-generated constructor stub
    }

}
