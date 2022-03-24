package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Classroom;

@Repository
public interface Classroomrepository extends JpaRepository<Classroom, Integer> {

}
