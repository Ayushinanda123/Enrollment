package com.an.Enrollment.mapper;

import com.an.Enrollment.dto.EnrollmentDto;
import com.an.Enrollment.entity.Enrollment;


public class EnrollmentMapper {
    public static Enrollment mapToEnrollment(EnrollmentDto enrollmentDto,Enrollment enrollment){
        enrollment.setEnrollId(enrollmentDto.getEnrollId());
        enrollment.setCourseId(enrollmentDto.getCourseId());
        enrollment.setStatus(enrollmentDto.getStatus());
        return enrollment;
    }
    public static EnrollmentDto mapToEnrollmentDto(Enrollment enrollment,EnrollmentDto enrollmentDto){
        enrollmentDto.setEnrollId(enrollment.getEnrollId());
        enrollmentDto.setCourseId(enrollment.getCourseId());
        enrollmentDto.setStatus(enrollment.getStatus());
        return enrollmentDto;
    }


}
