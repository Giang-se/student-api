package com.giangnxt.student_api.controller;

import com.giangnxt.student_api.model.request.StudentManageRequest;
import com.giangnxt.student_api.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentManageRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(request));
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

    @DeleteMapping
    public ResponseEntity<?> deleteStudents(@RequestBody List<Long> ids) {
        studentService.deleteStudents(ids);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        return ResponseEntity.ok().body(studentService.getStudent(id));
    }

    @GetMapping("/find")
    public ResponseEntity<?> findStudent(
            @RequestParam(name = "class", required = false) String studentClass,
            @RequestParam(name = "firstName", required = false) String firstName,
            @RequestParam(name = "lastName", required = false) String lastName
    ) {
        return ResponseEntity.ok().body(studentService.findStudent(studentClass, firstName, lastName));
    }
}
