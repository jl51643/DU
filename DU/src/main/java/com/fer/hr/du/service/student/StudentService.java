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

    public String createStudent(String firstname, String lastname, String email) {

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
                Student newStudent = new Student(firstname, lastname, email);
                studentRepository.save(newStudent);
                return "Student successfully created!";
            }
        }catch (Exception e){
            System.out.println(e);
            return "Some kind of error occured";
        }

        return "Some kind of error occured";
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }

    public Optional<Student> findByID(Long studentID){
        return studentRepository.findById(String.valueOf(studentID));
    }

    public Optional<Student> findByEmail(String email){
        return studentRepository.findByEmail(email);
    }

    public String updateStudent(Long studentID, String firstname, String lastname, String email){

        if (studentRepository.findById(String.valueOf(studentID)).isEmpty()){
            return "There is no student with that ID";
        }

        Student s = studentRepository.findById(String.valueOf(studentID)).get();

        if(firstname.length() > 30){
            return "Firstname too long";
        }
        if(lastname.length() > 30){
            return "Lastname too long";
        }
        if(lastname.length() > 30){
            return "Email too long";
        }
        if(findByEmail(email).isEmpty() || findByEmail(email).get().getId() == studentID){

            s.setFirstname(firstname);
            s.setLastname(lastname);
            s.setEmail(email);

            studentRepository.save(s);
            return "Student successfully updated";
        }

        return "Email taken";
    }

    public String deleteStudent(Long studentID){
        try {
            studentRepository.delete(studentRepository.findById(String.valueOf(studentID)).get());
        }catch (Exception e){
            return "Couldn't delete student, remove him from classrooms first";
        }

        return "Student successfully deleted";
    }

}
