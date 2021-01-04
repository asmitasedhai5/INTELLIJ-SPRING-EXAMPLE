package com.example.demo.controller;

import com.example.demo.model.Student;
import com.example.demo.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    private List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/student/{id}")
    private Student getStudentById(@PathVariable("id") Long studentId) {
        return studentService.getById(studentId);
    }

    @RequestMapping(value="/student", method= RequestMethod.POST)
    public ResponseEntity<?> insertStudent(@RequestBody Student createStudent){
        Student student = studentService.insert(createStudent);
        return new ResponseEntity<Student>(student, HttpStatus.CREATED);
    }

    @RequestMapping(value="/student/{id}", method= RequestMethod.PUT)
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long studentId, @RequestBody Student editStudent){
        Student student = studentService.update(studentId, editStudent);
        return new ResponseEntity<Student>(student, HttpStatus.OK);
    }

    @RequestMapping(value="/student/{id}", method= RequestMethod.DELETE)
    public ResponseEntity<?> deleteStudent(@PathVariable  Long id){
        studentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
