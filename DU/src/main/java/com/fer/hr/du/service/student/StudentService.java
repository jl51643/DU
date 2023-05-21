package com.fer.hr.du.service.student;

import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import com.fer.hr.du.repository.student.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findByID(Long studentID){
        return studentRepository.findById(String.valueOf(studentID));
    }


    public Student updateStudent(Long studentID, String firstname, String lastname, String email){
        Student s = studentRepository.findById(String.valueOf(studentID)).get();

        s.setFirstname(firstname);
        s.setLastname(lastname);
        s.setEmail(email);

        studentRepository.save(s);

        return s;
    }

    public void deleteStudent(Long studentID){
        studentRepository.delete(studentRepository.findById(String.valueOf(studentID)).get());
    }

}
