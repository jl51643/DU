package com.fer.hr.du.model.classroom;

import com.fer.hr.du.model.teacher.Teacher;
import jakarta.persistence.*;

import java.util.Optional;

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
