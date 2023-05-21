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
    public String getTeacherById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("userType", "teacher");
        model.addAttribute("user", teacherService.findById(id).get());
        return "userView";
    }

    @PostMapping("/{id}")
    public String updateTeacherById(@PathVariable("id") Long id, @RequestParam String firstname, String lastname, String email, String delete,Model model) {

        if (delete.equals("true")){
            System.out.println("BRISEM");
            String response = teacherService.deleteTeacher(id);
            model.addAttribute("userList", teacherService.findAllTeachers());
            model.addAttribute("userType", "teacher");
            model.addAttribute("success", response);
            return "userList";
        }

        String response = teacherService.updateTeacher(id, firstname, lastname, email);
        model.addAttribute("userType", "teacher");
        model.addAttribute("user", teacherService.findById(id).get());
        model.addAttribute("success", response);
        return "userView";
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        model.addAttribute("userType", "teacher");
        model.addAttribute("userList", teacherService.findAllTeachers());
        return "userList";
    }

    @PostMapping("")
    public String createTeacher(@RequestParam String firstname, String lastname, String email, Model model) {
        String response = teacherService.createTeacher(firstname, lastname, email);
        //return ResponseEntity.status(HttpStatus.CREATED).body(student);
        model.addAttribute("userType", "teacher");
        model.addAttribute("url", "/teacher");
        model.addAttribute("success", response);
        return "userForm";
    }
}
