package com.example.megha.services;


import com.example.megha.model.Course;
import com.example.megha.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRepository courseRepository;

    public List<Course> listAllCourses() {
        return courseRepository.findAll();
    }

    public Course save(Course course
    ) {
        return courseRepository.save(course);
    }

    public Course get(Long id) {
        return courseRepository.findById(id).get();
    }

    public Course update(Course course, Long rollNo) {
        Course cour = courseRepository.findById(rollNo).get();
        cour.setCourseName(course.getCourseName());
        cour.setCourseCreditHour(course.getCourseCreditHour());
        cour.setCoursePassMarks(course.getCoursePassMarks());
        cour.setCourseFullMarks(course.getCourseFullMarks());
        return courseRepository.save(cour);
    }

    public void delete(Long rollNo) {
        courseRepository.deleteById(rollNo);
    }
}
