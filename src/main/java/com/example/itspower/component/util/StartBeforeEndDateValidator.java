package com.example.itspower.component.util;

import com.jp.its.utils.ConverterUtils;
import org.apache.commons.beanutils.BeanUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Date;

public class StartBeforeEndDateValidator implements ConstraintValidator<StartBeforeEndDate, Object> {
  private String startDate;
  private String endDate;
  private String message;

  @Override
  public void initialize(final StartBeforeEndDate constraintAnnotation) {
    startDate = constraintAnnotation.startDate();
    endDate = constraintAnnotation.endDate();
    message = constraintAnnotation.message();
  }

  @Override
  public boolean isValid(final Object value, final ConstraintValidatorContext context) {
    boolean valid = true;
    try {
      final String startDateStr = BeanUtils.getProperty(value, startDate);
      final String endDateStr = BeanUtils.getProperty(value, endDate);

      Date date1 = ConverterUtils.formatStringToDate(startDateStr);
      Date date2 = ConverterUtils.formatStringToDate(endDateStr);

      if(date1 == null || date2 == null) return true;

      valid = !date1.after(date2);
    } catch (Exception e) {
      valid = false;
    }

    if (!valid) {
      context.buildConstraintViolationWithTemplate(message)
          .addPropertyNode(startDate)
          .addConstraintViolation()
          .disableDefaultConstraintViolation();
    }

    return valid;
  }
}
