package com.fer.hr.du.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
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
public class TeacherIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TeacherService teacherService;


    @Test
    @Order(1)
    void getCreateTeacherForm(){

        HttpResponse<String> getFormResponse = Unirest.get(createURLWithPort("/teacher"))
                .header("Content-Type", "application/json")
                .asString();

        System.out.println(getFormResponse.getBody());

        assertEquals(200, getFormResponse.getStatus());
        assertEquals(false, getFormResponse.getBody().isEmpty());
    }

    @Test
    @Order(2)
    void createTeacherTest(){

        HttpResponse<String> createTeacherResponse = Unirest.post(createURLWithPort("/teacher"))
                .multiPartContent()
                .field("firstname", "hrvoje")
                .field("lastname", "rom")
                .field("email", "hrvoje@fer.hr")
                .asString();

        System.out.println(createTeacherResponse.getBody());

        assertEquals(200, createTeacherResponse.getStatus());
        assertEquals(false, createTeacherResponse.getBody().isEmpty());

        assertEquals("hrvoje@fer.hr", teacherService.findByEmail("hrvoje@fer.hr").get().getEmail());
    }

    @Test
    @Order(3)
    void getNonExistantTeacherId(){

        HttpResponse<String> getTeacherResponse = Unirest.get(createURLWithPort("/teacher/12212112"))
                .asString();

        System.out.println(getTeacherResponse.getBody());

        assertEquals(500, getTeacherResponse.getStatus());
    }



    @AfterAll
    public void cleanUp(){
        Unirest.shutDown();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
