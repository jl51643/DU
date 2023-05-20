package com.fer.hr.du.controller.classroom;


import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.service.student.ClassroomService;
import com.fer.hr.du.service.student.StudentService;
import com.fer.hr.du.service.student.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private final ClassroomService classroomService;
    @Autowired
    private final TeacherService teacherService;
    @Autowired
    private final StudentService studentService;

    @Autowired
    public ClassroomController(ClassroomService classroomService, TeacherService teacherService, StudentService studentService){
        this.classroomService = classroomService;
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @GetMapping("")
    public String getTeacherForm(Model model) {
        model.addAttribute("teacherList", teacherService.findAllTeachers());
        return "classroomForm";
    }

    @GetMapping("/{id}")
    public String getStudentById(@PathVariable("id") Long id, Model model) {
        model.addAttribute("classroom", classroomService.findById(id).get());
        model.addAttribute("teacherList", teacherService.findAllTeachers());


        model.addAttribute("userType", "student");
        model.addAttribute("userList", classroomService.findStudents(id));
        model.addAttribute("success", "Success");

        return "classroomView";
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

    @PostMapping("/{id}")
    public String updateClassroom(@PathVariable("id") Long id, @RequestParam String name, Long teacherID, String delete, Model model){
        System.out.println("CLASSROOMID" + id);
        System.out.println("CLASSROOMNAME" + name);
        System.out.println("TEACHERID" + teacherID);
        System.out.println("DELETE" + delete);

        if (delete.equals("true")){
            System.out.println("BRISEM");
            classroomService.deleteClassroom(id);
            model.addAttribute("classroomList", classroomService.findAllClassrooms());
            return "classroomList";
        }
        classroomService.updateClassroom(id, name, teacherService.findById(teacherID).get());

        model.addAttribute("classroom", classroomService.findById(id).get());
        model.addAttribute("teacherList", teacherService.findAllTeachers());
        model.addAttribute("success", "Success");

        return "classroomView";
    }

    @PostMapping("/{id}/add")
    public String addStudent(@PathVariable("id") Long id/*, Long studentID*/, Model model){

        Long studentID = 1L;

        Student s = studentService.findByID(studentID).get();

        classroomService.addStudent(id, s);

        model.addAttribute("classroom", classroomService.findById(id).get());
        model.addAttribute("teacherList", teacherService.findAllTeachers());
        model.addAttribute("userType", "student");
        model.addAttribute("userList", classroomService.findStudents(id));
        model.addAttribute("success", "Success");

        return "classroomView";
    }
}
