package com.JobTrack.exception;

import com.JobTrack.DTO.ErrorResponseDTO;
import com.JobTrack.DTO.ValidationExceptionResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class globalException {

    @ExceptionHandler(JobNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleJobApplicationNotFound(JobNotFoundException exception,
                                                                   HttpServletRequest request) {

           ErrorResponseDTO error = new ErrorResponseDTO(

             LocalDateTime.now(),
             HttpStatus.NOT_FOUND.value(),
             HttpStatus.NOT_FOUND.getReasonPhrase(),
             exception.getMessage(),
             request.getRequestURI()

           );

           return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponseDTO>handleMethodArgNotValidException(HttpMessageNotReadableException exception,
                                                                            HttpServletRequest request){

        ErrorResponseDTO error = new ErrorResponseDTO(

                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "INVALID_REQUEST_BODY!",
                request.getRequestURI()

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionResponseDTO> handleValidException(MethodArgumentNotValidException exception,
                                                                               HttpServletRequest request){

        Map<String,String> fieldErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors()
                .forEach(error -> fieldErrors.put(error.getField(), error.getDefaultMessage()));

        ValidationExceptionResponseDTO error = new ValidationExceptionResponseDTO(

                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "VALIDATION FAILED!",
                request.getRequestURI(),
                fieldErrors

        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
