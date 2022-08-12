package com.example.megha.controllers;

import com.example.megha.model.Course;
import com.example.megha.model.Response;
import com.example.megha.services.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/course")
public class CourseController {
    private final CourseService courseService;

    @GetMapping("/")
    public String add () {
        return "course-index";
    }

    @ResponseBody
    @PostMapping("/courses")
    public Response findAllCoursees(){
        String message =null;
        boolean success = false;
        List<Course> courseList = new ArrayList<>();
        try{
            courseList = courseService.listAllCourses();
            success = true;
            message = "Successfully fetched course Data";

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(courseList,  message , success);
    }

    @ResponseBody
    @PostMapping("/edit")
    public  Response findCourseById(
            @RequestParam Long id
    ){
        String message = null;
        boolean success= true;
        Course course = new Course();
        try{
            course = courseService.get(id);
            success = true;
            message =  "Successfully fetch course by Id";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new Response(course, message, success);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response saveOrUpdateCourse (
            @ModelAttribute("course") Course cour
    ){
        Course course = null;
        String message = "";
        boolean success =false;
        try {
            if(cour.getId() != null) {
                course = courseService.get(cour.getId());
                course.setCourseName(cour.getCourseName());
                course.setCourseFullMarks(cour.getCourseFullMarks());
                course.setCoursePassMarks(cour.getCoursePassMarks());
                course.setCourseCreditHour(cour.getCourseCreditHour());
            } else {
                course = new Course();
                course.setCourseName(cour.getCourseName());
                course.setCourseFullMarks(cour.getCourseFullMarks());
                course.setCoursePassMarks(cour.getCoursePassMarks());
                course.setCourseCreditHour(cour.getCourseCreditHour());
            }
            course= courseService.save(course);
            message ="Successfully Save Course";
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Response(course, message, success );
    }

    @ResponseBody
    @PostMapping("/delete")
    public Response deleteSchool(
            @RequestParam Long id
    ){
        String message = null;
        boolean success = false;
        try{
            courseService.delete(id);
            success = true;
            message = "Successfully Deleted";
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(null, message, success);
    }
}
