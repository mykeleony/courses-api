package com.myke.courses.api.controller;

import com.myke.courses.domain.model.Course;
import com.myke.courses.domain.repository.CourseRepository;
import com.myke.courses.domain.service.CourseService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {
    private final CourseService courseService;
    private final CourseRepository courseRepository;

    @GetMapping
    public List<Course> listCourses() {
        return courseService.list();
    }

    @GetMapping("/{id}")
    public Course findCourse(@PathVariable Integer id) {
        return courseService.find(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@Valid @RequestBody Course course) {
        return courseService.create(course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Integer id) {
        courseService.delete(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer id, @Valid @RequestBody Course course) {
        return courseService.update(id, course);
    }

    public CourseController(CourseService courseService, CourseRepository courseRepository) {
        this.courseService = courseService;
        this.courseRepository = courseRepository;
    }
}
