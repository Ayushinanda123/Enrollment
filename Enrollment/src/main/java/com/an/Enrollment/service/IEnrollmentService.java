package com.an.Enrollment.service;

import com.an.Enrollment.dto.EnrollmentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface IEnrollmentService {


    //boolean updateStatus(@Valid EnrollmentDto enrollmentDto);

    void createEnrollment(EnrollmentDto enrollmentDto);

    EnrollmentDto fetchEnrollment(String enrollId);

    boolean updateEnrollment(EnrollmentDto enrollmentDto);

    boolean deleteEnrollment(String enrollId);

    List<EnrollmentDto> fetchAllEnrollment();
}
