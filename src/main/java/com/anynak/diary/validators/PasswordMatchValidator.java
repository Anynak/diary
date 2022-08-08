package com.anynak.diary.validators;

import com.anynak.diary.dto.UserRequest;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, UserRequest> {
    @Override
    public void initialize(PasswordMatch constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(UserRequest userRequest, ConstraintValidatorContext context) {
        if(userRequest.getPassword()==null || userRequest.getPassword().isBlank()){return false;}
        return userRequest.getPassword().equals(userRequest.getRepeatPassword());
    }
}
