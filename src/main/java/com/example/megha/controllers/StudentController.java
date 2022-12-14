package com.example.megha.controllers;

import com.example.megha.model.Course;
import com.example.megha.model.Response;
import com.example.megha.model.Student;
import com.example.megha.services.CourseService;
import com.example.megha.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/students/")
//        http://localhost:7080/api/v1/students

public class StudentController {

    private final StudentService studentService;
    private  final CourseService courseService;

    @PostMapping
    public Response createStudent(@RequestBody Student student){
       Student std =  studentService.save(student);
       String message = "Saved";
       boolean success =  true;
        return new Response(std, message, success );
    }

    @GetMapping("{id}")
    public Response getStudentById(@PathVariable  Long id){
        Student student = studentService.get(id);
        boolean success = true;
        String message = "Successfully Fetched data";
        return new Response(student, message, success );
    }

    @PutMapping("{id}")
    public Response updateStudent(
            @RequestBody Student student,
            @PathVariable Long id
    ) {
        String message ="Successfully updated Student";
        boolean success = true;
        Student studentEdit = studentService.update(student, id);
        return new Response(studentEdit, message, success);
    }

    @DeleteMapping("{id}")
    public Response deleteStudent(
            @PathVariable Long id
    ){
        studentService.delete(id);
        String message = "Successfully deleted student";
        boolean success = true;
        return new Response(null, message, success);
    }

    @PostMapping("/assign")
    public  Response assignCourse(
            @RequestParam Long courseId,
            @RequestParam Long studentId
    ){
        Student student = studentService.get(studentId);
        student.setCourse(new Course(courseId));
        studentService.save(student);
        String message ="Successfully assigned";
        return  new Response(student, message, true );
    }
}

