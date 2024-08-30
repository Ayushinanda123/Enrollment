package com.an.Enrollment.service.impl;

import com.an.Enrollment.dto.CourseDto;
import com.an.Enrollment.dto.EnrollmentDto;
import com.an.Enrollment.entity.Enrollment;
import com.an.Enrollment.exception.EnrollmentAlreadyExistsException;
import com.an.Enrollment.exception.EnrollmentNotFound;
import com.an.Enrollment.mapper.EnrollmentMapper;
import com.an.Enrollment.repository.EnrollmentRepository;
import com.an.Enrollment.service.IEnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EnrollmentServiceImpl implements IEnrollmentService {
    @Autowired
    private EnrollmentRepository repository;
    @Autowired
    private WebClient webClient;


    @Override
    public void createEnrollment(EnrollmentDto enrollmentDto) {
        Optional<Enrollment> employeeOptional = repository.findByEnrollId(enrollmentDto.getEnrollId() + "_" + enrollmentDto.getCourseId());

        if (employeeOptional.isPresent()) {
            throw new EnrollmentAlreadyExistsException("Employee enrollment already exists with id - " + enrollmentDto.getEnrollId() + "_" + enrollmentDto.getCourseId());
        }


        try{
            CourseDto courseDto = webClient.get().uri("http://localhost:8080/api/fetch?id=" + enrollmentDto.getCourseId()).retrieve().bodyToMono(CourseDto.class).block();
        }
        catch (Exception e){
            throw new EnrollmentAlreadyExistsException("Course does not exist with id - " + enrollmentDto.getCourseId());
        }

        Enrollment employee = EnrollmentMapper.mapToEnrollment(enrollmentDto, new Enrollment());
        repository.save(employee);
    }

    @Override
    public EnrollmentDto fetchEnrollment(String enrollId) {
        Enrollment enrollment = repository.findByEnrollId(enrollId).orElseThrow(
                () -> new EnrollmentNotFound("Employee enrollment does not exists for id - " + enrollId)
        );

        EnrollmentDto enrollmentDto = EnrollmentMapper.mapToEnrollmentDto(enrollment, new EnrollmentDto());
        return enrollmentDto;
    }

    @Override
    public boolean updateEnrollment(EnrollmentDto enrollmentDto) {
        boolean isUpdated = false;
        if(enrollmentDto.getEnrollId() == null || enrollmentDto.getCourseId() == null){
            return isUpdated;
        }
        Enrollment enrollment = repository.findByEnrollId(enrollmentDto.getEnrollId() + "_" + enrollmentDto.getCourseId()).orElseThrow(
                () -> new EnrollmentNotFound("Employee enrollment does not exists for id - " + enrollmentDto.getEnrollId() + "_" + enrollmentDto.getCourseId())
        );

        Enrollment updated = EnrollmentMapper.mapToEnrollment(enrollmentDto, enrollment);
        repository.save(updated);
        isUpdated = true;

        return isUpdated;
    }

    @Override
    public boolean deleteEnrollment(String enrollId) {
        boolean deleted=false;
        if(enrollId == null){
            return deleted;
        }
        repository.findByEnrollId(enrollId).orElseThrow(
                () -> new EnrollmentNotFound("Employee enrollment does not exists for id - " + enrollId)
        );
        repository.deleteByEnrollId(enrollId);
        deleted = true;
        return deleted;
    }

    @Override
    public List<EnrollmentDto> fetchAllEnrollment() {
        List<Enrollment> Repository = repository.findAll();
        List<EnrollmentDto> enrolDtos = new ArrayList<>();
        for(Enrollment enroll : Repository){
            EnrollmentDto enrollmentDto = EnrollmentMapper.mapToEnrollmentDto(enroll, new EnrollmentDto());
            enrolDtos.add(enrollmentDto);
        }
        return enrolDtos;
    }


}
