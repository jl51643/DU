package com.fer.hr.du.controller.teacher;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.service.student.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService){
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public String getTeacherForm(Model model) {
        model.addAttribute("userType", "teacher");
        model.addAttribute("url", "/teacher");
        return "userForm";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userType", "teacher");
        model.addAttribute("user", teacherService.findById(id).get());
        return "userView";
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        model.addAttribute("userType", "teacher");
        model.addAttribute("userList", teacherService.findAllTeachers());
        return "userList";
    }

    @PostMapping("")
    public String createStudent(@RequestParam String firstname, String lastname, String email, Model model) {
        Teacher newTeacher = new Teacher(firstname, lastname, email);
        Teacher newTeacher2 = teacherService.createTeacher(newTeacher);
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);
        model.addAttribute("userType", "teacher");
        model.addAttribute("url", "/teacher");
        model.addAttribute("success", "Success");
        return "userForm";
    }
}
