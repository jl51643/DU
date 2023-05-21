package com.fer.hr.du.service.student;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.repository.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    public void whenAddingStudent_shouldReturnStudent() {

        Student student = new Student("John", "Doe","john.doe@fer.hr");

        when(studentRepository.save(student)).thenReturn(student);

        assertEquals(studentService.createStudent(student), student);
    }
}
