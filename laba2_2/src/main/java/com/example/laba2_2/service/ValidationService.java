package com.example.laba2_2.service;

import com.example.laba2_2.exception.UnsupportedCodeException;
import com.example.laba2_2.exception.ValidationFailedException;
import com.example.laba2_2.model.Request;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

@Service
public interface ValidationService {
    void isValid(BindingResult bindingResult) throws ValidationFailedException;
    void checkUnsupportedCode(Request request) throws UnsupportedCodeException;
}
