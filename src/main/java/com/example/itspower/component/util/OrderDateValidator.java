package com.example.itspower.component.util;

import com.example.itspower.repository.ReportRepository;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@RequiredArgsConstructor
public class OrderDateValidator implements ConstraintValidator<OrderDateException, String> {
    private final ReportRepository reportRepository;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (reportRepository.findByOrderDate(s).isPresent()) {
            return false;
        }
        return true;
    }
}
