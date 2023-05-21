package com.fer.hr.du.repository.student;

import com.fer.hr.du.model.student.Student;
import com.fer.hr.du.model.teacher.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, String> {

    Teacher findByEmail(String teacherEmail);
}
