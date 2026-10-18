package com.example.laba6_2H_db.controller;

import com.example.laba6_2H_db.entity.Student;
import com.example.laba6_2H_db.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping("/students/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @PostMapping("/students")
    public ResponseEntity<String> saveStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return new ResponseEntity<>("Студент успешно добавлен", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка при добавлении студента", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/students")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        try {
            studentService.saveStudent(student);
            return new ResponseEntity<>("Данные студента успешно обновлены", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ошибка при обновлении данных", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable int id) {
        Student student = studentService.getStudent(id);
        if (student == null) {
            return new ResponseEntity<>("Студент с таким ID не найден", HttpStatus.NOT_FOUND);
        }
        studentService.deleteStudent(id);
        return new ResponseEntity<>("Студент успешно удален", HttpStatus.OK);
    }
}