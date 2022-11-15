package com.example.itspower.component.constrant;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = FieldsMatchValidator.class)
@Target({ElementType.TYPE})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface FieldMatch {

    String message() default "Fields values don't match!";

    String field();

    String fieldMatch();

    EFieldMatch matchBy() default EFieldMatch.NONE;

    String matchValue() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List {
        FieldMatch[] value();
    }
}
