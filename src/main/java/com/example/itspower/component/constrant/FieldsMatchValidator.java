package com.example.itspower.component.constrant;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class FieldsMatchValidator implements ConstraintValidator<FieldMatch, Object> {

    private String field;
    private String fieldMatch;
    private EFieldMatch matchBy;
    private String matchValue;

    public void initialize(FieldMatch constraintAnnotation) {
        this.field = constraintAnnotation.field();
        this.fieldMatch = constraintAnnotation.fieldMatch();
        this.matchBy = constraintAnnotation.matchBy();
        this.matchValue = constraintAnnotation.matchValue();
    }

    public boolean isValid(Object value, ConstraintValidatorContext context) {

        Object fieldValue = new BeanWrapperImpl(value)
                .getPropertyValue(field);
        Object fieldMatchValue = new BeanWrapperImpl(value)
                .getPropertyValue(fieldMatch);

        switch (matchBy) {
            case EXIST:
                return fieldMatchValue != null || fieldValue == null;
            case VALUE_MATCH:
                return valueMatch(fieldValue, fieldMatchValue);
            case DATE_AFTER:
                return dateAfter(fieldValue, fieldMatchValue);
            case DATE_BEFORE:
                return dateBefore(fieldValue, fieldMatchValue);
            case REQUIRED_IF:
                return requiredIf(fieldValue, fieldMatchValue);
            case NONE:
            default:
                return true;
        }
    }

    private boolean valueMatch(Object source, Object target) {
        return (target != null && target.toString().equalsIgnoreCase(matchValue))
                || source == null;
    }

    private boolean dateAfter(Object source, Object target) {
        Date d1 = (Date) source;
        Date d2 = (Date) target;
        return (target != null && source != null && d1.after(d2)) || (source == null);
    }

    private boolean dateBefore(Object source, Object target) {
        Date d1 = (Date) source;
        Date d2 = (Date) target;
        return (target != null && source != null && d1.before(d2)) || (source == null);
    }

    private boolean requiredIf(Object source, Object target) {
        List<String> lst = Arrays.asList(matchValue.split(","));
        return target == null || (source != null && lst.contains(target.toString()));
    }
}
