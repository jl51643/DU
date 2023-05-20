package com.fer.hr.du.controller.classroom;


import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.service.student.ClassroomService;
import com.fer.hr.du.service.student.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private final ClassroomService classroomService;
    @Autowired
    private final TeacherService teacherService;

    @Autowired
    public ClassroomController(ClassroomService classroomService, TeacherService teacherService){
        this.classroomService = classroomService;
        this.teacherService = teacherService;
    }

    @GetMapping("")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacherList", teacherService.findAllTeachers());
        return "classroomForm";
    }

    @GetMapping("/list")
    public String getTeachers(Model model) {
        System.out.println("ERROR" + classroomService.findAllClassrooms());
        model.addAttribute("classroomList", classroomService.findAllClassrooms());
        return "classroomList";
    }

    @PostMapping("")
    public String createClassroom(@RequestParam String name, Long teacherID, Model model) {
        System.out.println("TEACHERI" + teacherService.findAllTeachers());
        System.out.println("Tea" + teacherService.findById(teacherID));
        Classroom classroom1 = new Classroom(name, teacherService.findById(teacherID).get());
        Classroom classroom2 = classroomService.createClassroom(classroom1);
        model.addAttribute("teacherList", teacherService.findAllTeachers());
        model.addAttribute("success", "Success");

        return "classroomForm";

    }
}
