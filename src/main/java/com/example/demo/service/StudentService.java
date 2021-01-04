package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students;
    }

    public Student getById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        return student;
    }

    public Student insert(Student student) {
        System.out.println(student.toString());
        return studentRepository.save(student);
    }

    public Student update(Long studentId, Student editStudentModel) {
        Student student = studentRepository.findById(studentId).get();
        if (student != null) {
            student.setName(editStudentModel.getName());
            student.setAddress((editStudentModel.getAddress()));
            student.setContact(editStudentModel.getContact());
        }
        return studentRepository.save(student);
    }

    public void delete(Long studentId) {
        studentRepository.deleteById(studentId);
    }

}
