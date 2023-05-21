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

    public Teacher createTeacher(Teacher teacher) {
        return teacherRepository.save(teacher);
    }

    public List<Teacher> findAllTeachers(){
        return teacherRepository.findAll();
    }

    public Optional<Teacher> findById(Long teacherID){
        return teacherRepository.findById(String.valueOf(teacherID));
    }
    public Teacher findByEmail (String teacherEmail){
        return teacherRepository.findByEmail(teacherEmail);
    }


    public Teacher updateTeacher(Long teacherID, String firstname, String lastname, String email){
        Teacher t = teacherRepository.findById(String.valueOf(teacherID)).get();

        t.setFirstname(firstname);
        t.setLastname(lastname);
        t.setEmail(email);

        teacherRepository.save(t);

        return t;
    }

    public void deleteTeacher(Long teacherID){
        teacherRepository.delete(teacherRepository.findById(String.valueOf(teacherID)).get());
    }
}
