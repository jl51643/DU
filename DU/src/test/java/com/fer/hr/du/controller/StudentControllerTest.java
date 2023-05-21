package com.fer.hr.du.controller;

import com.fer.hr.du.controller.student.StudentController;
import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.service.student.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class StudentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        studentController = new StudentController(studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
    }

    @Test
    public void getStudentFormTest() throws Exception {
        mockMvc.perform(get("/student"))
                .andExpect(status().isOk())
                .andExpect(view().name("userForm"))
                .andExpect(model().attribute("userType", "student"))
                .andExpect(model().attribute("url", "/student"));
    }

    @Test
    public void testGetStudentById() throws Exception {
        Long studentId = 1L;
        Student student = new Student();
        student.setId(studentId);
        student.setFirstname("Hrvoje");
        student.setLastname("Rom");
        student.setEmail("hrvoje@fer.hr");

        when(studentService.findByID(studentId)).thenReturn(Optional.of(student));

        mockMvc.perform(get("/student/{id}", studentId))
                .andExpect(status().isOk())
                .andExpect(view().name("userView"))
                .andExpect(model().attribute("userType", "student"))
                .andExpect(model().attribute("user", student));
    }

    @Test
    public void testGetStudents() throws Exception {
        // Mock the student list
        List<Student> students = Arrays.asList(
                new Student(1L, "Hrvoje", "Rom", "hrvoje@fer.hr"),
                new Student(2L, "Jane", "Smith", "josip@fer.hr")
        );

        when(studentService.findAllStudents()).thenReturn(students);

        // Perform the GET request
        mockMvc.perform(get("/student/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(model().attribute("userType", "student"))
                .andExpect(model().attribute("userList", students));
    }

}
