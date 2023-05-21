package com.fer.hr.du.service.student;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.repository.student.StudentRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class StudentServiceTest {

    @Mock
    StudentRepository studentRepository;

    @InjectMocks
    StudentService studentService;

    @Test
    public void successfulAddStudentTest() {

        String firstname = "Josip";
        String lastname = "Lukacevic";
        String email = "josip@fer.hr";


        when(studentRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        assertEquals("Student successfully created!", studentService.createStudent(firstname, lastname, email));
    }

    @Test
    public void findAllStudentsTest() {

        Student josip1 = new Student ("Josip", "Lukacevic", "josip1@fer.hr");
        Student josip2 = new Student ("Josip", "Lukacevic", "josip2@fer.hr");
        Student josip3 = new Student ("Josip", "Lukacevic", "josip3@fer.hr");

        ArrayList<Student> list = new ArrayList<>();
        list.add(josip1);
        list.add(josip2);
        list.add(josip3);

        when(studentRepository.findAll()).thenReturn(list);

        assertEquals(3, studentService.findAllStudents().size());
    }

    @Test
    public void findStudentByEmail() {

        Student hrvoje = new Student ("Hrvoje", "Rom", "hrvoje@fer.hr");
        Student josip = new Student ("Josip", "Lukacevic", "josip@fer.hr");


        when(studentRepository.findByEmail(any(String.class))).thenReturn(Optional.of(josip));

        assertEquals("Josip", studentService.findByEmail("josip@fer.hr").get().getFirstname());
    }
}
