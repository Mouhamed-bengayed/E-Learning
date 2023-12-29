package com.example.co2.Controller;

import com.example.co2.Entite.Courses;
import com.example.co2.Entite.Userco2;
import com.example.co2.Service.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/courses")
public class CoursesController {

    @Autowired
    CoursesService coursesService;

    @GetMapping("/list-courses")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<Courses> ListCourses() {
        return coursesService.getAllCourses();
    }
    @PostMapping("/add-courses")
    public Courses addCourses(@RequestBody @Valid Courses c1) {
        return coursesService.addCourses(c1);
    }

    @DeleteMapping("/delete-courses/{idCourses}")
    public void deleteCourses(@PathVariable("idCourses") Long idCourses) {
        coursesService.deleteCourses(idCourses);
    }




}
