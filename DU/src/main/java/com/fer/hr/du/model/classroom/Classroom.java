package com.fer.hr.du.model.classroom;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "classroom")
public class Classroom {

    public Classroom(String name, Teacher teacher){
        this.name = name;
        this.teacher = teacher;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Teacher teacher;

    @OneToMany
    private Set<Student> students = new HashSet<>();

    public Classroom() {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void addStudent(Student student){
        this.students.add(student);
    }

    public void removeStudent(Student student){
        this.students.remove(student);
    }

    public List<Student> getStudents(){
        return this.students.stream().toList();
    }
    public Classroom(String name, Optional<Teacher> byId) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Classroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacher=" + teacher +
                '}';
    }
}
