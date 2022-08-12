package com.example.megha.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "course_name", nullable = false)
    private String courseName;

    @Column(name = "course_credit_hour", nullable = false)
    private Integer courseCreditHour;

    @Column(name = "course_full_marks", nullable = false)
    private Integer courseFullMarks;

    @Column(name = "course_pass_marks", nullable = false)
    private Integer coursePassMarks;

    public  Course(Long id){
        this.id  = id;
    }

}
