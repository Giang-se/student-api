package com.giangnxt.student_api.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "student")
public class Student {
    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String studentClass;
}
