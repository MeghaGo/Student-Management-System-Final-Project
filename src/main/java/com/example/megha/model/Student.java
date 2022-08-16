package com.example.megha.model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "student", uniqueConstraints = @UniqueConstraint(columnNames = "entranceRollNo"))
//uniqueconstraints helps to make entranceRollNo as unique for all students
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private Integer entranceRollNo;

    private String firstName;

    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date DOB;

    private String address;

    private Float entranceScore;

    private String identityNo;

    @Column(unique = true)
    private String loggedInEmail;

    private String assignedMessage;

    @OneToOne
    private  Course course;

}
