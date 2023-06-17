package com.giangnxt.student_api.repository;

import com.giangnxt.student_api.model.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByStudentClass(String studentClass);

    List<Student> findByFirstName(String firstName);

    List<Student> findByLastName(String lastName);
}
