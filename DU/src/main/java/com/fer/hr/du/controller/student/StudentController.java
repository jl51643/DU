package com.fer.hr.du.controller.student;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.service.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getStudentForm(Model model) {
        model.addAttribute("userType", "student");
        model.addAttribute("url", "/student");
        return "userForm";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userType", "student");
        model.addAttribute("student", studentService.findByID(id).get());
        return "userView";
    }

    @GetMapping("/list")
        public String getStudents(Model model) {
        model.addAttribute("userType", "student");
        model.addAttribute("userList", "student");model.addAttribute("userList", studentService.findAllStudents());
        return "userList";
    }

    @PostMapping("")
    public String createStudent(@RequestParam String firstname, String lastname, String email, Model model) {
        Student newStudent = new Student(firstname, lastname, email);
        Student newStudent2 = studentService.createStudent(newStudent);
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);
        model.addAttribute("userType", "student");
        model.addAttribute("url", "/student");
        model.addAttribute("success", "Success");
        return "userForm";
    }
}
