package com.example.demo.config.validations.customAnnotations;

import com.example.demo.config.validations.validators.IpAddressValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IpAddressValidator.class)
public @interface IpAddress {

    String message() default "{IpAddress.invalid}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
