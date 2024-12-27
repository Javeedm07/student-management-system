package com.example.student.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.student.entity.Student;
import com.example.student.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("student", new Student());
        return "addStudent";
    }

    @PostMapping("/submitStudent")
    public String addStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "addStudent";
        }
        studentService.addStudent(student);
        return "redirect:/viewAll";
    }

    @GetMapping("/viewAll")
    public String viewAllStudents(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "viewAllStudent";
    }

    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable String id) {  
        studentService.deleteStudent(id);
        return "redirect:/viewAll";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable String id, Model model) {  
        Student student = studentService.getStudentById(id);
        model.addAttribute("student", student);
        return "updateStudent";
    }

    @PostMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable String id, @Valid @ModelAttribute Student student, 
                              BindingResult result) {  
        if (result.hasErrors()) {
            return "updateStudent";
        }
        studentService.updateStudent(id, student);
        return "redirect:/viewAll";
    }

    @GetMapping("/search")
    public String showSearchPage(Model model) {
        model.addAttribute("student", null);
        return "searchStudent";
    }

    @GetMapping("/searchResult")
    public String searchStudent(@RequestParam String studentId, Model model) {
        Student student = studentService.getStudentById(studentId);
        if (student != null) {
            model.addAttribute("student", student);
        } else {
            model.addAttribute("student", null);
        }
        return "searchStudent";
    }
}
