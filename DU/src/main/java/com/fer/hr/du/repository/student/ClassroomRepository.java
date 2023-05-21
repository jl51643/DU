package com.fer.hr.du.repository.student;


import com.fer.hr.du.model.classroom.Classroom;
import com.fer.hr.du.model.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, String> {
}
