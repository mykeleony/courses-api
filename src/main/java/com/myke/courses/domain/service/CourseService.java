package com.myke.courses.domain.service;

import com.myke.courses.domain.exception.BusinessException;
import com.myke.courses.domain.model.Course;
import com.myke.courses.domain.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CourseService {
    private CourseRepository courseRepository;

    @Transactional
    public List<Course> list() {
        return courseRepository.findAll();
    }

    @Transactional
    public Course find(Integer id) {
        return courseRepository.findById(id)
                .orElseThrow( () -> new BusinessException("The specified course does not exist."));
    }

    @Transactional
    public Course create(Course course) {
        return courseRepository.save(course);
    }

    @Transactional
    public void delete(Integer id) {
        courseRepository.deleteById(id);
    }

    @Transactional
    public ResponseEntity<Course> update(Integer id, Course course) {
        if (! courseRepository.existsById(id))
            return ResponseEntity.notFound().build();

        course.setId(id);

        return ResponseEntity.ok(courseRepository.save(course));
    }

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }
}
