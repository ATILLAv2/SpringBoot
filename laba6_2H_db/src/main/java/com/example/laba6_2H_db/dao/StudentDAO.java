package com.example.laba6_2H_db.dao;

import com.example.laba6_2H_db.entity.Student;
import java.util.List;

public interface StudentDAO {
    List<Student> getAllStudents();
    Student saveStudent(Student student);
    Student getStudent(int id);
    void deleteStudent(int id);
}