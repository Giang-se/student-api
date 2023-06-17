package com.giangnxt.student_api.controller;

import com.giangnxt.student_api.model.request.StudentManageRequest;
import com.giangnxt.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentManageRequest request) {
        return ResponseEntity.ok().body(studentService.createStudent(request));
    }

    @PutMapping
    public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentManageRequest request) {
        return ResponseEntity.ok().body(studentService.updateStudent(request));
    }

    @PatchMapping
    public ResponseEntity<?> updateStudentPartially(@Valid @RequestBody StudentManageRequest request) {
        return ResponseEntity.ok().body(studentService.updateStudentPartially(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.deleteStudent(id));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }
}
