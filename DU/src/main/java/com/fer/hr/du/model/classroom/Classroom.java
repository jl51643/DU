package com.fer.hr.du.model.classroom;

import com.fer.hr.du.model.teacher.Teacher;
import jakarta.persistence.*;

@Entity
@Table(name = "classroom")
public class Classroom {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    private Teacher teacher;
}
