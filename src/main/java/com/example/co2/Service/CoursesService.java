package com.example.co2.Service;

import com.example.co2.Dao.CoursesRepository;
import com.example.co2.Entite.Courses;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.ServiceImpl.CoursesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CoursesService implements CoursesServiceImpl {
    @Autowired
    CoursesRepository coursesRepository;

    @Override
    public List<Courses> getAllCourses() {
        return coursesRepository.findAll();
    }

    @Override
    public Courses addCourses(Courses courses1) {
    Courses savesCourses=coursesRepository.save(courses1);
        return savesCourses;
    }
    @Override
    public Courses deleteCourses(Long id) {
        Optional<Courses> courses = coursesRepository.findById(id);
        if(courses.isPresent()){
            return courses.get();
        }else
        {
            return null;
        }
    }
    }





