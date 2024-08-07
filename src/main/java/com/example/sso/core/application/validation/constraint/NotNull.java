package com.example.sso.core.application.validation.constraint;

import java.util.Objects;

import com.example.sso.core.application.validation.ConstraintValidator;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class NotNull implements ConstraintValidator {

    private final String message;

    @Override
    public boolean validate(Object value) {
        return Objects.nonNull(value);
    }
    
}
