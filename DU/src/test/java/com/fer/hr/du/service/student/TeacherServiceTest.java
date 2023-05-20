package com.fer.hr.du.service.student;


import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TeacherServiceTest {

    @Mock
    TeacherRepository teacherRepository;

    @InjectMocks
    TeacherService teacherService;

    @Test
    public void whenAddingNewTeacher_shouldReturnTeacher() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        when(teacherRepository.save(teacher)).thenReturn(teacher);

        assertEquals(teacherService.createTeacher(teacher), teacher);
    }
}
