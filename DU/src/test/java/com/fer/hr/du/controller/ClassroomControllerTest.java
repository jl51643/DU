package com.fer.hr.du.controller;

import com.fer.hr.du.controller.classroom.ClassroomController;
import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.service.student.ClassroomService;
import com.fer.hr.du.service.student.StudentService;
import com.fer.hr.du.service.student.TeacherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ClassroomControllerTest {

    @Mock
    private ClassroomService classroomService;
    @Mock
    private TeacherService teacherService;
    @Mock
    private StudentService studentService;

    private ClassroomController classroomController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        classroomController = new ClassroomController(classroomService, teacherService, studentService);
        mockMvc = MockMvcBuilders.standaloneSetup(classroomController).build();
    }

    @Test
    public void getClassroomFormTest() throws Exception {
        Model model = mock(Model.class);

        mockMvc.perform(get("/classroom"))
                .andExpect(status().isOk())
                .andExpect(view().name("classroomForm"))
                .andExpect(model().attributeExists("teacherList"));
    }

    @Test
    public void testGetClassrooms() {
        List<Classroom> classrooms = new ArrayList<>();
        classrooms.add(new Classroom("Classroom 1", new Teacher()));
        classrooms.add(new Classroom("Classroom 2", new Teacher()));

        when(classroomService.findAllClassrooms()).thenReturn(classrooms);

        Model model = mock(Model.class); // Create a mock Model object

        String viewName = classroomController.getClassrooms(model);

        assertEquals("classroomList", viewName);

        // Perform assertions or verifications on the model object
        verify(model, times(1)).addAttribute("classroomList", classrooms);
    }
}