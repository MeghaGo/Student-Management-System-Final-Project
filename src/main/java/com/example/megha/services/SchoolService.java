package com.example.megha.services;


import com.example.megha.model.School;
import com.example.megha.repository.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {
    private final SchoolRepository schoolRepository;

    public List<School> listAll(){
        return schoolRepository.findAll();
    }

    public School save(School sl) {
        School school =schoolRepository.save(sl);
        return  school;
    }

    public School get(long id) {
        return schoolRepository.findById(id).get();
    }

    public void delete (long id) {
        schoolRepository.deleteById(id);
    }
}


