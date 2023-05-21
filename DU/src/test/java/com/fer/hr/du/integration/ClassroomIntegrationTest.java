package com.fer.hr.du.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fer.hr.du.service.student.ClassroomService;
import com.fer.hr.du.service.student.StudentService;
import com.fer.hr.du.service.student.TeacherService;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertEquals;


@TestPropertySource(locations = "classpath:application.properties")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ClassroomIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private ClassroomService classroomService;

    @Autowired
    private TeacherService teacherService;

    @Test
    @Order(1)
    void getCreateClassroomForm(){

        HttpResponse<String> getFormResponse = Unirest.get(createURLWithPort("/classroom"))
                .header("Content-Type", "application/json")
                .asString();

        System.out.println(getFormResponse.getBody());

        assertEquals(200, getFormResponse.getStatus());
        assertEquals(false, getFormResponse.getBody().isEmpty());
    }

    @Test
    @Order(2)
    void createClassroomWithoutTeacher(){

        HttpResponse<String> createClassroomResponse = Unirest.post(createURLWithPort("/classroom"))
                .multiPartContent()
                .field("name", "Hrvojeva ucionica")
                //.field("teacherID", "1")
                .asString();

        System.out.println(createClassroomResponse.getBody());

        assertEquals(500, createClassroomResponse.getStatus());
        assertEquals(false, createClassroomResponse.getBody().isEmpty());
    }

    @Test
    @Order(3)
    void createClassroomWithTeacher(){
        HttpResponse<String> createTeacherResponse = Unirest.post(createURLWithPort("/teacher"))
                .multiPartContent()
                .field("firstname", "Hrvoje")
                .field("lastname", "Rom")
                .field("email", "hrvoje@fer.hr")
                .asString();

        assertEquals(200, createTeacherResponse.getStatus());
        Long teacherID = teacherService.findByEmail("hrvoje@fer.hr").get().getId();

        HttpResponse<String> createClassroomResponse = Unirest.post(createURLWithPort("/classroom"))
                .multiPartContent()
                .field("name", "Hrvojeva ucionica")
                .field("teacherID", String.valueOf(teacherID))
                .asString();

        System.out.println(createClassroomResponse.getBody());

        assertEquals(200, createClassroomResponse.getStatus());
        assertEquals(false, createClassroomResponse.getBody().isEmpty());
    }

    @Test
    @Order(4)
    void deleteTeacherInClassroom(){
        HttpResponse<String> createTeacherResponse = Unirest.post(createURLWithPort("/teacher"))
                .multiPartContent()
                .field("firstname", "Hrvoje")
                .field("lastname", "Rom")
                .field("email", "hrvoje@fer.hr")
                .asString();

        assertEquals(200, createTeacherResponse.getStatus());
        Long teacherID = teacherService.findByEmail("hrvoje@fer.hr").get().getId();

        HttpResponse<String> createClassroomResponse = Unirest.post(createURLWithPort("/classroom"))
                .multiPartContent()
                .field("name", "Hrvojeva ucionica")
                .field("teacherID", String.valueOf(teacherID))
                .asString();

        assertEquals(200, createClassroomResponse.getStatus());
        assertEquals(false, createClassroomResponse.getBody().isEmpty());

        HttpResponse<String> deleteTeacherResponse = Unirest.post(createURLWithPort("/teacher/" + teacherID))
                .multiPartContent()
                .field("delete", "true")
                .field("firstname", "Hrvojeva ucionica")
                .field("lastname", "Hrvojeva ucionica")
                .field("email", "Hrvojeva ucionica")
                .asString();


        // U ispisu se moze naci <div>Couldn&#39;t delete teacher, delete classroom first or set another teacher</div>
        System.out.println(deleteTeacherResponse.getBody());

        assertEquals(200, deleteTeacherResponse.getStatus());
        assertEquals(false, deleteTeacherResponse.getBody().isEmpty());
    }

    @Test
    @Order(5)
    void deleteTeacherNotInClassroom(){
        HttpResponse<String> createTeacherResponse = Unirest.post(createURLWithPort("/teacher"))
                .multiPartContent()
                .field("firstname", "Hrvoje")
                .field("lastname", "Rom")
                .field("email", "hrvoje@fer.hr")
                .asString();

        assertEquals(200, createTeacherResponse.getStatus());
        Long teacherID = teacherService.findByEmail("hrvoje@fer.hr").get().getId();

        HttpResponse<String> deleteTeacherResponse = Unirest.post(createURLWithPort("/teacher/" + teacherID))
                .multiPartContent()
                .field("delete", "true")
                .field("firstname", "Hrvojeva ucionica")
                .field("lastname", "Hrvojeva ucionica")
                .field("email", "Hrvojeva ucionica")
                .asString();


        // U ispisu se moze naci <div>Teacher successfully deleted</div>
        System.out.println(deleteTeacherResponse.getBody());

        assertEquals(200, deleteTeacherResponse.getStatus());
        assertEquals(false, deleteTeacherResponse.getBody().isEmpty());
    }


    @AfterAll
    public void cleanUp(){
        Unirest.shutDown();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
