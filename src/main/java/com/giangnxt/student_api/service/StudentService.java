package com.giangnxt.student_api.service;

import com.giangnxt.student_api.exception.StudentNotFoundException;
import com.giangnxt.student_api.model.entity.Student;
import com.giangnxt.student_api.model.request.StudentManageRequest;
import com.giangnxt.student_api.model.response.StudentResponse;
import com.giangnxt.student_api.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class StudentService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StudentRepository studentRepository;

    public StudentResponse createStudent(StudentManageRequest request) {
        Student student = modelMapper.map(request, Student.class);
        studentRepository.save(student);
        return modelMapper.map(student, StudentResponse.class);
    }

    public StudentResponse updateStudent(StudentManageRequest request) {
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new StudentNotFoundException(request.getId()));

        modelMapper.map(request, student);
        studentRepository.save(student);
        return modelMapper.map(student, StudentResponse.class);
    }

    public StudentResponse updateStudentPartially(StudentManageRequest request) {
        Student student = studentRepository.findById(request.getId())
                .orElseThrow(() -> new StudentNotFoundException(request.getId()));

        if (!Objects.isNull(request.getFirstName())) {
            student.setFirstName(request.getFirstName());
        }
        if (!Objects.isNull(request.getLastName())) {
            student.setLastName(request.getLastName());
        }
        if (!Objects.isNull(request.getStudentClass())) {
            student.setStudentClass(request.getStudentClass());
        }

        studentRepository.save(student);
        return modelMapper.map(student, StudentResponse.class);
    }

    public StudentResponse deleteStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));

        studentRepository.delete(student);
        return modelMapper.map(student, StudentResponse.class);
    }

    public StudentResponse getStudent(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new StudentNotFoundException(id));
        return modelMapper.map(student, StudentResponse.class);
    }

    public void deleteStudents(List<Long> ids) {
        ids.stream()
                .forEach(id -> {
                    Student student = studentRepository.findById(id)
                            .orElseThrow(() -> new StudentNotFoundException(id));
                    studentRepository.delete(student);
                });

    }
}
