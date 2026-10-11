package com.example.laba2_2.service;

import com.example.laba2_2.exception.UnsupportedCodeException;
import com.example.laba2_2.exception.ValidationFailedException;
import com.example.laba2_2.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public class RequestValidationService implements ValidationService{

    @Override
    public void isValid(BindingResult bindingResult) throws ValidationFailedException {
        if (bindingResult.hasErrors()) {
            throw new
                    ValidationFailedException(bindingResult.getFieldError().toString());
        }
    }
    public void checkUnsupportedCode(Request request) throws UnsupportedCodeException {
        if ("123".equals(request.getUid())) {
            throw new UnsupportedCodeException("Не поддерживаемая ошибка");
        }
    }
}
