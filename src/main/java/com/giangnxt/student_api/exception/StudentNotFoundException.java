package com.giangnxt.student_api.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class StudentNotFoundException extends RuntimeException {
    @Getter
    private Long id;

    public StudentNotFoundException(Long id) {
        super(String.format("Student not found with Id: '%s'", id));
        this.id = id;
    }

}
