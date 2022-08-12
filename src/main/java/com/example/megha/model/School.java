package com.example.megha.model;


import lombok.ToString;
import javax.persistence.*;

@Entity
@ToString
@Table(name="tbl_school")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id",nullable = false)
    private Long id;

    @Column(name="school_id")
    private int schoolId;

    @Column(name="school_name")
    private String schoolName;

    @Column(name="school_level")
    private String schoolLevel;

    private String address;
    private String city;
    private Long phone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public School(Long id, int schoolId, String schoolName, String schoolLevel, String address, String city, Long phone) {
        this.id = id;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.schoolLevel = schoolLevel;
        this.address = address;
        this.city = city;
        this.phone = phone;
    }

    public School() {
    }
}

