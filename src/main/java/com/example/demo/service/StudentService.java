package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
// The 'Service' annotation will tell Spring that this class needs to be instantiated as a 'Spring Bean'
// Semantically, this could be a 'Component', however a Service annotation is more specific in this example
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());

        if (studentByEmail.isPresent()) {
            throw new IllegalStateException("Email is taken.");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists = studentRepository.existsById(studentId);

        if (!exists) {
            throw new IllegalStateException("Student with id " + studentId + " does not exist.");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    // The 'Transactional' annotation sends the requested Entity into a managed state,
    // the query from 'StudentRepository' is not necessary.
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " does not exist."));

        if (null != name && name.length() > 0) {
            student.setName(name);
        }

        if (null != email && email.length() > 0) {
            Optional<Student> existingEmailCheck = studentRepository.findStudentByEmail(email);
            if (existingEmailCheck.isPresent()) {
                throw new IllegalStateException("Email is taken.");
            }
            student.setEmail(email);
        }
    }
}
