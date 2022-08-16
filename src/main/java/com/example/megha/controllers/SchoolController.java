package com.example.megha.controllers;

import com.example.megha.model.Response;
import com.example.megha.model.School;
import com.example.megha.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService service;

    @GetMapping("/")
    public String add (Model model) {
        return "new";
    }

    @ResponseBody
    @PostMapping("/schools")
    public Response findAllSchools(){
        String message =null;
        boolean success = false;
        List<School> schoolList = new ArrayList<>();
        try{
            schoolList = service.listAll();
            success = true;
            message = "Successfully fetched School Data";

        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(schoolList,  message , success);
    }

    @ResponseBody
    @PostMapping("/edit")
    public  Response findSchoolById(
            @RequestParam Long id
    ){
        String message = null;
        boolean success= true;
        School school = new School();
        try{
            school = service.get(id);
            success = true;
            message =  "Successfully fetch school by Id";
        }catch (Exception e){
            e.printStackTrace();
        }
        return  new Response(school, message, success);
    }

    @ResponseBody
    @PostMapping("/add")
    public Response saveOrUpdateSchool (
            @ModelAttribute("school") School sl
    ){
        School school = null;
        String message = "";
        boolean success =false;
        try {
            if(sl.getId() != null) {
                school = service.get(sl.getId());
                school.setSchoolId(sl.getSchoolId());
                school.setSchoolName(sl.getSchoolName());
                school.setSchoolLevel(sl.getSchoolLevel());
                school.setAddress(sl.getAddress());
                school.setCity(sl.getCity());
                school.setPhone(sl.getPhone());
            } else {
                school = new School();
                school.setId(sl.getId());
                school.setSchoolId(sl.getSchoolId());
                school.setSchoolName(sl.getSchoolName());
                school.setSchoolLevel(sl.getSchoolLevel());
                school.setAddress(sl.getAddress());
                school.setCity(sl.getCity());
                school.setPhone(sl.getPhone());
            }
            school= service.save(school);
            message ="Successfully Save School";
            success = true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return new Response(school, message, success );
    }

    @ResponseBody
    @PostMapping("/delete")
    public Response deleteSchool(
            @RequestParam Long id
    ){
        String message = null;
        boolean success = false;
        try{
            service.delete(id);
            success = true;
            message = "Successfully Deleted";
        }catch (Exception e){
            e.printStackTrace();
        }
        return new Response(null, message, success);
    }
}
