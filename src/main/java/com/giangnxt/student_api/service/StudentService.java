package com.giangnxt.student_api.service;

import com.giangnxt.student_api.model.entity.Student;
import com.giangnxt.student_api.model.request.StudentManageRequest;
import com.giangnxt.student_api.model.response.StudentResponse;
import com.giangnxt.student_api.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
