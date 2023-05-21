package com.fer.hr.du.controller;

import com.fer.hr.du.controller.teacher.TeacherController;
import com.fer.hr.du.model.teacher.Teacher;
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
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class TeacherControllerTest {

    @Mock
    private TeacherService teacherService;

    private TeacherController teacherController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        teacherController = new TeacherController(teacherService);
        mockMvc = MockMvcBuilders.standaloneSetup(teacherController).build();
    }

    @Test
    public void getTeacherFormTest() throws Exception {
        Model model = mock(Model.class); // Create a mock Model object

        mockMvc.perform(get("/teacher"))
                .andExpect(status().isOk())
                .andExpect(view().name("userForm"))
                .andExpect(model().attributeExists("userType"))
                .andExpect(model().attributeExists("url"));
    }

    @Test
    void getTeacherByIdTest() throws Exception {
        Long teacherId = 1L;
        Teacher teacher = new Teacher();
        teacher.setId(teacherId);
        teacher.setFirstname("Josip");
        teacher.setLastname("Lukacevic");
        teacher.setEmail("joisp.lukacevic@fer.hr");

        given(teacherService.findById(teacherId)).willReturn(Optional.of(teacher));

        mockMvc.perform(get("/teacher/{id}", teacherId))
                .andExpect(status().isOk())
                .andExpect(view().name("userView"))
                .andExpect(model().attribute("userType", "teacher"))
                .andExpect(model().attribute("user", teacher));
    }

    @Test
    public void getTeachersTest() throws Exception {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher(1L, "Hrvoje", "Rom", "hrvoje@fer.hr"));
        teachers.add(new Teacher(2L, "Josip", "Lukacevic", "josip@fer.hr"));

        given(teacherService.findAllTeachers()).willReturn(teachers);

        mockMvc.perform(get("/teacher/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("userList"))
                .andExpect(model().attribute("userType", "teacher"))
                .andExpect(model().attribute("userList", teachers));
    }


}
