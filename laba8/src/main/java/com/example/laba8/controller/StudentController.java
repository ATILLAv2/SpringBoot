package com.example.laba8.controller;

import com.example.laba8.entity.Student;
import com.example.laba8.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/list")
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        return "list-students";
    }

    @GetMapping("/addStudentForm")
    public String addStudentForm(Model model) {
        model.addAttribute("student", new Student());
        return "add-student-form";
    }

    @PostMapping("/saveStudent")
    public String saveStudent(@ModelAttribute Student student) {
        studentRepository.save(student);
        return "redirect:/list";
    }

    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam Long studentId, Model model) {
        Optional<Student> student = studentRepository.findById(studentId);
        student.ifPresent(value -> model.addAttribute("student", value));
        return "add-student-form";
    }

    @GetMapping("/deleteStudent")
    public String deleteStudent(@RequestParam Long studentId) {
        studentRepository.deleteById(studentId);
        return "redirect:/list";
    }
}