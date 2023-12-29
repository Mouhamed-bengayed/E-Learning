package com.example.co2.Service.ServiceImpl;

import com.example.co2.Entite.Courses;
import com.example.co2.Entite.Userco2;

import java.util.List;

public interface CoursesServiceImpl {

    public List<Courses> getAllCourses() ;

    public Courses addCourses(Courses courses);

    public Courses deleteCourses(Long id);

    }
