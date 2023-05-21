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
    private final TeacherRepository teacherRepository;

    @Autowired
    public ClassroomService(ClassroomRepository classroomRepository, TeacherRepository teacherRepository){
        this.classroomRepository = classroomRepository;
        this.teacherRepository = teacherRepository;
    }

    public String createClassroom(String classroomName, Teacher teacher){

        try {
            if (classroomName.length() > 30){
                return "Classroom name is too long";
            }
            if (teacherRepository.findById(String.valueOf(teacher.getId())).isEmpty()){
                return "That teacher doesn't exist";
            }
            if(!classroomRepository.findByName(classroomName).isEmpty()){
                return "Classroom with that name already exists";
            }
            if(classroomRepository.findByTeacher(teacher).size() > 3){
                return "Teacher is overworked, select some other teacher";
            }
            Classroom classroom = new Classroom(classroomName, teacherRepository.findById(String.valueOf(teacher.getId())).get());
            classroomRepository.save(classroom);
            return "Classroom created";
        }catch (Exception e){
            System.out.println(e);
            return "Some kind of error occured";
        }
    }

    public List<Classroom> findAllClassrooms(){
        return classroomRepository.findAll();
    }

    public Optional<Classroom> findById(Long classroomID){
        return classroomRepository.findById(String.valueOf(classroomID));
    }

    public String updateClassroom(Long classroomID, String classroomName, Teacher teacher){

        if(classroomRepository.findById(String.valueOf(classroomID)).isEmpty()){
            return "There is no such classroom";
        }

        Classroom c = classroomRepository.findById(String.valueOf(classroomID)).get();

        try {
            if (classroomName.length() > 30){
                return "Classroom name is too long";
            }
            if (teacherRepository.findById(String.valueOf(teacher.getId())).isEmpty()){
                return "That teacher doesn't exist";
            }
            if(!classroomRepository.findByName(classroomName).isEmpty() && classroomRepository.findByName(classroomName).get().getId() != classroomID){
                return "Classroom with that name already exists";
            }
            if(classroomRepository.findByTeacher(teacher).size() > 3){
                return "Teacher is overworked, select some other teacher";
            }
            c.setName(classroomName);
            c.setTeacher(teacherRepository.findById(String.valueOf(teacher.getId())).get());

            classroomRepository.save(c);
            return "Classroom updated";
        }catch (Exception e){
            System.out.println(e);
            return "Some kind of error occured";
        }
    }

    public String deleteClassroom(Long classroomID){
        try {
            classroomRepository.delete(classroomRepository.findById(String.valueOf(classroomID)).get());
            return "Classroom successfully deleted";
        }catch (Exception e){
            System.out.println(e);
            return "Error occured while deleting classroom";
        }
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
