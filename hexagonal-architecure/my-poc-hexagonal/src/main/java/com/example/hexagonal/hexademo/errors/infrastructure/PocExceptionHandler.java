package com.example.hexagonal.hexademo.errors.infrastructure;

import com.example.hexagonal.hexademo.errors.domain.MissingMandatoryValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class PocExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String ERRORS = "errors";

    @ExceptionHandler(MissingMandatoryValueException.class)
    public ProblemDetail handleMissingMandatoryValueException(
            MissingMandatoryValueException missingMandatoryValueException
    ) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                HttpStatus.BAD_REQUEST,
                "One or more fields were invalid. See 'errors' for details."
        );
        problem.setTitle("Bean validation error");
        problem.setProperty(ERRORS, buildErrors(missingMandatoryValueException));
        return problem;
    }

    private Map<String, String> buildErrors(MissingMandatoryValueException exception) {
        return Collections.singletonMap(exception.getFieldName(), exception.getErrorCode());
    }
}
