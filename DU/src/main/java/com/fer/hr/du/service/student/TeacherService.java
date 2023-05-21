package com.fer.hr.du.service.student;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {

    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherService(TeacherRepository teacherRepository){
        this.teacherRepository = teacherRepository;
    }

    public String createTeacher(String firstname, String lastname, String email) {
        try {
            if(firstname.length() > 30){
                return "Firstname too long";
            }
            if(lastname.length() > 30){
                return "Lastname too long";
            }
            if(email.length() > 30){
                return "Email too long";
            }
            if (!findByEmail(email).isEmpty()){
                return "Email taken";
            }
            if(findByEmail(email).isEmpty()){
                Teacher newTeacher = new Teacher(firstname, lastname, email);
                teacherRepository.save(newTeacher);
                return "Teacher successfully created!";
            }
        }catch (Exception e){
            System.out.println(e);
            return "Some kind of error occured";
        }

        return "Some kind of error occured";
    }

    public List<Teacher> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long teacherID){
        return teacherRepository.findById(String.valueOf(teacherID));
    }
    public Optional<Teacher> findByEmail (String teacherEmail){
        return teacherRepository.findByEmail(teacherEmail);
    }


    public String updateTeacher(Long teacherID, String firstname, String lastname, String email){
        /*Teacher t = teacherRepository.findById(String.valueOf(teacherID)).get();

        t.setFirstname(firstname);
        t.setLastname(lastname);
        t.setEmail(email);

        teacherRepository.save(t);

        return t;*/

        if (teacherRepository.findById(String.valueOf(teacherID)).isEmpty()){
            return "There is no student with that ID";
        }

        Teacher t = teacherRepository.findById(String.valueOf(teacherID)).get();

        if(firstname.length() > 30){
            return "Firstname too long";
        }
        if(lastname.length() > 30){
            return "Lastname too long";
        }
        if(lastname.length() > 30){
            return "Email too long";
        }
        if(findByEmail(email).isEmpty() || findByEmail(email).get().getId() == teacherID){

            t.setFirstname(firstname);
            t.setLastname(lastname);
            t.setEmail(email);

            teacherRepository.save(t);
            return "Teacher successfully updated";
        }

        return "Email taken";
    }

    public String deleteTeacher(Long teacherID){
        //teacherRepository.delete(teacherRepository.findById(String.valueOf(teacherID)).get());
        try {
            teacherRepository.delete(teacherRepository.findById(String.valueOf(teacherID)).get());
        }catch (Exception e){
            return "Couldn't delete teacher, delete classroom first or set another teacher";
        }

        return "Teacher successfully deleted";
    }
}
