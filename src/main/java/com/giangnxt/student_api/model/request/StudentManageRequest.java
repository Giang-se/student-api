package com.giangnxt.student_api.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentManageRequest {
    @NotNull
    private Long id;
    private String firstName;
    private String lastName;
    private String studentClass;
}
