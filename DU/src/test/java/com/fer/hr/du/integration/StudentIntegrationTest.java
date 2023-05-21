package com.fer.hr.du.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fer.hr.du.service.student.StudentService;
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
public class StudentIntegrationTest {
    @LocalServerPort
    private int port;

    @Autowired
    private StudentService studentService;


    @Test
    @Order(1)
    void getCreateStudentForm(){

        HttpResponse<String> getFormResponse = Unirest.get(createURLWithPort("/student"))
                .header("Content-Type", "application/json")
                .asString();

        System.out.println(getFormResponse.getBody());

        assertEquals(200, getFormResponse.getStatus());
        assertEquals(false, getFormResponse.getBody().isEmpty());
    }

    @Test
    @Order(2)
    void createStudentTest(){

        HttpResponse<String> createStudentResponse = Unirest.post(createURLWithPort("/student"))
                .multiPartContent()
                .field("firstname", "hrvoje")
                .field("lastname", "rom")
                .field("email", "hrvoje@fer.hr")
                .asString();

        System.out.println(createStudentResponse.getBody());

        assertEquals(200, createStudentResponse.getStatus());
        assertEquals(false, createStudentResponse.getBody().isEmpty());

        assertEquals("hrvoje@fer.hr", studentService.findByEmail("hrvoje@fer.hr").get().getEmail());
    }

    @Test
    @Order(3)
    void getNonExistantStudentId(){

        HttpResponse<String> getStudentResponse = Unirest.get(createURLWithPort("/student/12212112"))
                .asString();

        System.out.println(getStudentResponse.getBody());

        assertEquals(500, getStudentResponse.getStatus());
    }

    @AfterAll
    public void cleanUp(){
        Unirest.shutDown();
    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}
