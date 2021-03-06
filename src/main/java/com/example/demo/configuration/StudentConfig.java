package com.example.demo.configuration;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository) {
        return args -> {
            Student aydan = new Student(
                    "Aydan Jordan",
                    LocalDate.of(2007, JULY, 14),
                    "jordana@school.edu");

            Student sara = new Student(
                    "Sara De La Cruz",
                    LocalDate.of(1986, DECEMBER, 2),
                    "delacruzs@school.edu");

            Student peter = new Student(
                    "Peter Parker",
                    LocalDate.of(2001, AUGUST, 10),
                    "parkerp@school.edu");

            repository.saveAll(List.of(aydan, sara, peter));
        };
    }
}
