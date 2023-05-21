package com.fer.hr.du.service.student;


import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.TeacherRepository;
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
public class TeacherServiceTest {

    @Mock
    TeacherRepository teacherRepository;

    @InjectMocks
    TeacherService teacherService;

    @Test
    public void successfulAddTeacherTest() {

        String firstname = "Milan";
        String lastname = "Milanovic";
        String email = "mile@fer.hr";


        when(teacherRepository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        assertEquals("Teacher successfully created!", teacherService.createTeacher(firstname, lastname, email));
    }

    @Test
    public void findAllTeachersTest() {

        Teacher josip1 = new Teacher("Josip", "Lukacevic", "josip1@fer.hr");
        Teacher josip2 = new Teacher("Josip", "Lukacevic", "josip2@fer.hr");
        Teacher josip3 = new Teacher("Josip", "Lukacevic", "josip3@fer.hr");

        ArrayList<Teacher> list = new ArrayList<>();
        list.add(josip1);
        list.add(josip2);
        list.add(josip3);

        when(teacherRepository.findAll()).thenReturn(list);

        assertEquals(3, teacherService.findAllTeachers().size());
    }

    @Test
    public void findTeacherByEmail() {

        Teacher hrvoje = new Teacher("Hrvoje", "Rom", "hrvoje@fer.hr");
        Teacher josip = new Teacher("Josip", "Lukacevic", "josip@fer.hr");


        when(teacherRepository.findByEmail(any(String.class))).thenReturn(Optional.of(hrvoje));

        assertEquals("Hrvoje", teacherService.findByEmail("hrvoje@fer.hr").get().getFirstname());
    }
}
