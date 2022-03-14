package com.example.demo.service;

import com.example.demo.model.Student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

public class StudentService {

    public List<Student> getStudents() {
        return List.of(
                new Student(
                        1L,
                        "Aydan Jordan",
                        17,
                        LocalDate.of(2007, Month.JULY, 14),
                        "jordana@school.edu"));
    }

}
