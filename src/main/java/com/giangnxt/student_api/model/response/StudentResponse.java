package com.giangnxt.student_api.model.response;

import lombok.Data;

@Data
public class StudentResponse {
    private Long id;
    private String firstName;
    private String lastName;
    private String studentClass;
}
