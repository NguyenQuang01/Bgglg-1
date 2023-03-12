package com.example.itspower.component.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = OrderDateValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OrderDateException {
    String message() default "Report is exit by orderDate!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
