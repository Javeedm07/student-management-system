package com.example.student.service;

import com.example.student.entity.Student;
import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {  
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);  
    }

    public void deleteStudent(String id) {  
        studentRepository.deleteById(id);
    }

    public void updateStudent(String id, Student student) {  
        student.setId(id);  
        studentRepository.save(student);
    }
}

