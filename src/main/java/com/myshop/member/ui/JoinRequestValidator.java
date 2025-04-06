package com.myshop.member.ui;

import com.myshop.member.command.domain.JoinRequest;

import javax.validation.ConstraintViolation;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Set;

import org.springframework.validation.Validator;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

public class JoinRequestValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return false;
    }

    @Override
    public void validate(Object target, Errors errors) {
        JoinRequest joinRequest = (JoinRequest) target;

        // 비어 있는 값 확인
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword", "required");

        // 비밀번호 확인 일치 여부
        if (!joinRequest.getPassword().equals(joinRequest.getConfirmPassword())) {
            errors.rejectValue("confirmPassword", "password.mismatch", "비밀번호가 일치하지 않습니다.");
        }
    }

}