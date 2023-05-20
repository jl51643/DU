package com.fer.hr.du.service.student;

import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.ClassroomRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClassroomServiceTest {

    @Mock
    ClassroomRepository classroomRepository;

    @InjectMocks
    ClassroomService classroomService;

    @Test
    public void whenAddingNewClassroom_shouldReturnClassroom() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        Classroom classroom = new Classroom("biology class", teacher);

        when(classroomRepository.save(classroom)).thenReturn(classroom);

        assertEquals(classroomService.createClassroom(classroom), classroom);
    }
}
