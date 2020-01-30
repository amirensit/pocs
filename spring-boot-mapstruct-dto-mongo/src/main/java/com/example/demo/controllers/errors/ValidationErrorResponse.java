package com.example.demo.controllers.errors;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<Violation> violations = new ArrayList<>();

    public List<Violation> getViolations() {
        return violations;
    }

    public void setViolations(List<Violation> violations) {
        this.violations = violations;
    }

    public ValidationErrorResponse addViolation(Violation violation) {
        this.violations.add(violation);
        return this;
    }
}

 class Violation {

    private  String fieldName;

    private  String message;

    private String code;

     public Violation(String fieldName, String message) {
         this.fieldName = fieldName;
         this.message = message;
     }

     public Violation(String fieldName, String message, String code) {
         this.fieldName = fieldName;
         this.message = message;
         this.code = code;
     }

     public String getCode() {
         return code;
     }

     public void setCode(String code) {
         this.code = code;
     }

     public String getFieldName() {
         return fieldName;
     }

     public void setFieldName(String fieldName) {
         this.fieldName = fieldName;
     }

     public String getMessage() {
         return message;
     }

     public void setMessage(String message) {
         this.message = message;
     }
 }
