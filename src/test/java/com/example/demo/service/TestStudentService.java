package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TestStudentService {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAllStudentsTest() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student(1L,"Asta","ktm","9843298221"));
        studentList.add(new Student(2L,"Abhishek","lmj","9843298222"));
        studentList.add(new Student(3L,"Avinash","pok","9843298223"));

        when(studentRepository.findAll()).thenReturn(studentList);

        List<Student> students = studentService.getAllStudents();

        assertEquals(3, students.size());
    }

    @Test
    public void getStudentByIdTest() {
        when(studentRepository.findById(4L)).thenReturn(java.util.Optional.of(new Student(4L, "Asmita", "ktm", "9843298221")));

        Student student = studentService.getById(4L);

        assertEquals("Asmita", student.getName());
        assertEquals("ktm", student.getAddress());
        assertEquals("9843298221", student.getContact());
    }

    @Test
    public void insertTest() {
        Student student = new Student(5L,"Bikash","Bgl","9801153602");
        when(studentRepository.save(student)).thenReturn(student);
        Student studentFromService = studentService.insert(student);

        assertEquals("Bikash", studentFromService.getName());
        assertEquals("Bgl", studentFromService.getAddress());
        assertEquals("9801153602", studentFromService.getContact());
    }

    @Test
    public void updateTest() {
        Student student = new Student(5L,"Bikash","Bgl","9801153602");
        when(studentRepository.findById(5L)).thenReturn(java.util.Optional.of(student));

        when(studentRepository.save(student)).thenReturn(student);

        Student studentUpdated = studentService.update(5L, student);

        assertEquals("Bikash", studentUpdated.getName());
        assertEquals("Bgl", studentUpdated.getAddress());
        assertEquals("9801153602", studentUpdated.getContact());
    }

    @Test
    public void deleteTest() {
        Student student = new Student(5L,"Bikash","Bgl","9801153602");
        when(studentRepository.findById(5L)).thenReturn(Optional.of(student));
        studentService.delete(5L);
        Mockito.verify(studentRepository, Mockito.times(1)).deleteById(5L);
    }
}
