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
        model.addAttribute("user", studentService.findByID(id).get());
        return "userView";
    }

    @PostMapping("/{id}")
    public String updateStudentById(@PathVariable("id") Long id, @RequestParam String firstname, String lastname, String email, String delete,Model model) {

        if (delete.equals("true")){
            System.out.println("BRISEM");
            String response = studentService.deleteStudent(id);
            model.addAttribute("userList", studentService.findAllStudents());
            model.addAttribute("userType", "student");
            model.addAttribute("success", response);
            return "userList";
        }

        String response = studentService.updateStudent(id, firstname, lastname, email);
        model.addAttribute("userType", "student");
        model.addAttribute("user", studentService.findByID(id).get());
        model.addAttribute("success", response);

        return "userView";

    }

    @GetMapping("/list")
        public String getStudents(Model model) {
        model.addAttribute("userType", "student");
        //model.addAttribute("userList", "student");
        model.addAttribute("userList", studentService.findAllStudents());
        return "userList";
    }

    @PostMapping("")
    public String createStudent(@RequestParam String firstname, String lastname, String email, Model model) {

        String response = studentService.createStudent(firstname, lastname, email);
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);
        model.addAttribute("userType", "student");
        model.addAttribute("url", "/student");
        model.addAttribute("success", response);
        return "userForm";
    }
}
