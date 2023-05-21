package com.fer.hr.du.service.student;


import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.ClassroomRepository;
import com.fer.hr.du.repository.student.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClassroomService {

    private final ClassroomRepository classroomRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository){
        this.classroomRepository = classroomRepository;
    }

    public Classroom createClassroom(Classroom classroom){
        return classroomRepository.save(classroom);
    }

    public List<Classroom> findAllClassrooms(){
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Long classroomID){
        return classroomRepository.findById(String.valueOf(classroomID));
    }

    public Classroom updateClassroom(Long classroomID, String classroomName, Teacher teacher){
        Classroom c = classroomRepository.findById(String.valueOf(classroomID)).get();

        c.setName(classroomName);
        c.setTeacher(teacher);

        classroomRepository.save(c);

        return c;
    }

    public void deleteClassroom(Long classroomID){
        classroomRepository.delete(classroomRepository.findById(String.valueOf(classroomID)).get());
    }

    public void addStudent(Long classroomID, Student student){
        Classroom c = classroomRepository.findById(String.valueOf(classroomID)).get();

        c.addStudent(student);

        classroomRepository.save(c);
    }

    public void removeStudent(Long classroomID, Student student){
        Classroom c = classroomRepository.findById(String.valueOf(classroomID)).get();

        c.removeStudent(student);

        classroomRepository.save(c);
    }

    public List<Student> findStudents(Long classroomID){
        Classroom c = classroomRepository.findById(String.valueOf(classroomID)).get();

        return c.getStudents();
    }
}
