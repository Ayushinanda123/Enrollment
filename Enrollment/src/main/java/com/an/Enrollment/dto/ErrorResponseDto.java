package com.an.Enrollment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponseDto {
    private String apiPath;
    private String errorMessage;
    private HttpStatus statusCode;
    private LocalDateTime timestamp;
}
