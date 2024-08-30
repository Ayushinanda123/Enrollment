package com.an.Enrollment.controller;

import com.an.Enrollment.dto.EnrollmentDto;
import com.an.Enrollment.dto.ResponseDto;
import com.an.Enrollment.entity.Enrollment;
import com.an.Enrollment.service.IEnrollmentService;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;


@Validated
@RestController
@RequestMapping("/api")

public class EnrollmentController {

    @Value("${build.version}")
    private String buildVersion;

    @Autowired
    private IEnrollmentService iEnrollmentService;

    @Operation(
            description = "create new",
            summary = "summary"
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createEnrollment(@RequestBody @Valid EnrollmentDto enrollmentDto) {

        iEnrollmentService.createEnrollment(enrollmentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ResponseDto("Created successfully", "201")
        );
    }

    @GetMapping("/fetch")
    public ResponseEntity<EnrollmentDto> fetchEnrollment(String enrollId){
        EnrollmentDto enrollmentDto=iEnrollmentService.fetchEnrollment(enrollId);
        return ResponseEntity.status(HttpStatus.OK).body(enrollmentDto);
    }
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateEnrollment(@RequestBody @Valid EnrollmentDto enrollmentDto) {
        boolean isUpdated = iEnrollmentService.updateEnrollment(enrollmentDto);
        if (isUpdated) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Updated successfully", "203")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not updated", "501")
            );
        }

    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteEnrollment(String enrollId) {
        boolean isDeleted = iEnrollmentService.deleteEnrollment(enrollId);
        if (isDeleted) {
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseDto("Deleted successfully", "203")
            );
        }
        else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseDto("Not updated", "501")
            );
        }
    }

    @GetMapping("/fetch-all")
    public List<EnrollmentDto> fetchAllEmployee(){
        // Write code to fetch all employees;
        return iEnrollmentService.fetchAllEnrollment();
    }

//    @PutMapping("/updateStatus")
//    public ResponseEntity<ResponseDto> updateStatus(@RequestBody @Valid EnrollmentDto enrollmentDto){
//        boolean isUpdated= iEnrollmentService.updateStatus(enrollmentDto);
//        if(isUpdated){
//            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDto("Updated Successfully","203"));
//        }
//        else{
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseDto("Not updated","501"));
//        }
//    }

    @GetMapping("/greet")
    public String greet(){
        return "Hello world!";
    }

    @GetMapping("/build-info")
    public String buildInfo(){
        return buildVersion;
    }
}
