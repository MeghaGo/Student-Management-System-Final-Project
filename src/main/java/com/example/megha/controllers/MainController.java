package com.example.megha.controllers;

import com.example.megha.model.Course;
import com.example.megha.model.Student;
import com.example.megha.model.User;
import com.example.megha.repository.StudentRepository;
import com.example.megha.repository.UserRepository;
import com.example.megha.services.CourseService;
import com.example.megha.services.StudentService;
import com.example.megha.services.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;


@Controller
@RequiredArgsConstructor
public class MainController {
    private final UserServiceImpl userService;
    private final StudentService studentService;
    private final StudentRepository studentRepository;

    private  final CourseService courseService;
    @Autowired
    HttpSession session;
    private final UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/users")
    public String adminControl(Model model) {
        List<Student> allList = studentService.listAllStudent();
        List<Course> courses = courseService.listAllCourses();
        model.addAttribute("liststudent", allList);
        model.addAttribute("courses", courses);
        return "applicants";
    }
    @GetMapping("/accept/{rollNo}/{loggedInEmail}")
    public String accept(@PathVariable("rollNo") Long rollNo, @PathVariable("loggedInEmail") String loggedInEmail){
        session.setAttribute("message","Accept");
        Student student= studentService.get(loggedInEmail);
        student.setAssignedMessage("Accepted");
        studentRepository.save(student);
        return "redirect:/users?success";
    }
    @RequestMapping("/delete/{rollNo}/{loggedInEmail}")
    public String delete(@PathVariable("rollNo") Long rollNo, @PathVariable("loggedInEmail") String loggedInEmail) {
//        studentService.delete(rollNo);
        Student student= studentService.get(loggedInEmail);
        System.out.println(student);
        student.setAssignedMessage("Rejected");
        studentRepository.save(student);
//       session.setAttribute("meessageStudent",student);
        session.setAttribute("message","Delete");
        return "redirect:/users?success";
    }
//    @GetMapping("/apply")
//    public User apply(@ModelAttribute UserRegistrationDto userRegistrationDto){
//        model.addAttribute("student",userRegistrationDto);
//        return userService.save(userRegistrationDto);
//    }

    @GetMapping("/welcome")
    public String welcome(Model model) {
        session.setAttribute("username", getLoggedinUserName());
        Student student=new Student();
        User user = userRepository.findByEmail(((String) session.getAttribute("username")));
        Optional<Student> optionalStudent= Optional.ofNullable(studentService.get(((String) session.getAttribute("username"))));
        if(optionalStudent.isPresent() || user.isAdmin() )
        {
            student=studentService.get((String) session.getAttribute("username"));
            if(student != null && student.getAssignedMessage()==null){
                student.setAssignedMessage(" ");
            }
            //studentService.save(student);
            model.addAttribute("student", student);
            return "welcome";
        }

        return "redirect:/login?failed";
    }

    private String getLoggedinUserName() {
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            return ((UserDetails) principal).getUsername();
        }

        return principal.toString();
    }
}