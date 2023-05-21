package com.fer.hr.du.service.student;

import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.ClassroomRepository;
import com.fer.hr.du.repository.student.TeacherRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ClassroomServiceTest {

    @Mock
    ClassroomRepository classroomRepository;
    @Mock
    TeacherRepository teacherRepository;

    @InjectMocks
    ClassroomService classroomService;

    @Test
    public void successfulAddClassroomTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        String classroomName = "Biology class";

        when(teacherRepository.findById(any(String.class))).thenReturn(Optional.of(teacher));

        assertEquals("Classroom created", classroomService.createClassroom(classroomName, teacher));
    }

    @Test
    public void classroomNameTooLongTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        String classroomName = "Biology classssssssssssssssssssssssssssssss";

        //when(teacherRepository.findById(any(String.class))).thenReturn(Optional.of(teacher));

        assertEquals("Classroom name is too long", classroomService.createClassroom(classroomName, teacher));
    }

    @Test
    public void teacherDoesntExistTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        String classroomName = "Biology class";

        when(teacherRepository.findById(any(String.class))).thenReturn(Optional.empty());

        assertEquals("That teacher doesn't exist", classroomService.createClassroom(classroomName, teacher));
    }

    @Test
    public void classroomNameExistsTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");
        Classroom classroom = new Classroom("Biology class", teacher);

        String classroomName = "Biology class";
        Teacher teacherSecond = new Teacher("Johnny", "Doner", "john.doe22@fer.he");

        when(classroomRepository.findByName(classroomName)).thenReturn(Optional.of(classroom));
        when(teacherRepository.findById(any(String.class))).thenReturn(Optional.of(teacherSecond));

        assertEquals("Classroom with that name already exists", classroomService.createClassroom(classroomName, teacherSecond));
    }

    @Test
    public void teacherIsOverworkedTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");

        String classroomName = "Biology class";

        when(classroomRepository.findByTeacher(teacher)).thenReturn(Arrays.asList(new Classroom[10]));
        when(teacherRepository.findById(any(String.class))).thenReturn(Optional.of(teacher));

        assertEquals("Teacher is overworked, select some other teacher", classroomService.createClassroom(classroomName, teacher));
    }

    @Test
    public void findAllClassroomsTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");
        String classroomName = "Biology class";

        when(classroomRepository.findAll()).thenReturn(Arrays.asList(new Classroom[10]));

        assertEquals(10, classroomService.findAllClassrooms().size());
    }

    @Test
    public void findClassroomByIdTest() {

        Teacher teacher = new Teacher("John", "Doe", "john.doe@fer.he");
        Classroom classroom = new Classroom("Biology class", teacher);

        when(classroomRepository.findById(any(String.class))).thenReturn(Optional.of(classroom));

        assertEquals(Classroom.class, classroomService.findById(classroom.getId()).get().getClass());
    }
}
