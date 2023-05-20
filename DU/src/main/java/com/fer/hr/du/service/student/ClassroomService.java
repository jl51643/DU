package com.fer.hr.du.service.student;


import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.repository.student.ClassroomRepository;
import com.fer.hr.du.repository.student.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
