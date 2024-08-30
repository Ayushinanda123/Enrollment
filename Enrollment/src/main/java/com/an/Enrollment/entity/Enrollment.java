package com.an.Enrollment.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Table(name="enrollment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Boolean status;
    private String enrollId;
    private String courseId;
    private String employeeId;
}
