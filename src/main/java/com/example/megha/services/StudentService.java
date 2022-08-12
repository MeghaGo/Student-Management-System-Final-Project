package com.example.megha.services;

import com.example.megha.model.Student;
import com.example.megha.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public List<Student> listAllStudent() {
        return studentRepository.findAll();
    }

    public Student save(Student std) {
        return studentRepository.save(std);
    }

    public Student get(Long id) {
        return studentRepository.findById(id).get();
    }
    public Student get(String email){
        return studentRepository.findByLoggedInEmail(email);
    }

    public Student update(Student std, Long id) {
        Student student = studentRepository.findById(id).get();
        student.setEntranceRollNo(std.getEntranceRollNo());
        student.setFirstName(std.getFirstName());
        student.setLastName(std.getLastName());
        student.setAddress(std.getAddress());
        student.setDOB(std.getDOB());
        student.setEntranceScore(std.getEntranceScore());
        student.setIdentityNo(std.getIdentityNo());
        return studentRepository.save(student);
    }

    Student student = new Student();
    public void delete(Long id) {
        studentRepository.deleteById(id);
    }
}
