package com.example.laba2_2.exception;

import com.example.laba2_2.model.Codes;
import com.example.laba2_2.model.ErrorCodes;
import com.example.laba2_2.model.ErrorMessages;
import com.example.laba2_2.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationFailedException.class)
    public ResponseEntity<Response> handleValidation(ValidationFailedException e) {
        return buildErrorResponse(ErrorCodes.VALIDATION_EXCEPTION, ErrorMessages.VALIDATION_ERROR);
    }

    @ExceptionHandler(UnsupportedCodeException.class)
    public ResponseEntity<Response> handleUnsupportedCode(UnsupportedCodeException e) {
        return buildErrorResponse(ErrorCodes.UNSUPPORTED_CODE_EXCEPTION, ErrorMessages.UNSUPPORTED_CODE_ERROR);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleUnknown(Exception e) {
        return buildErrorResponse(ErrorCodes.UNKNOWN_EXCEPTION, ErrorMessages.UNKNOWN_ERROR);
    }

    private ResponseEntity<Response> buildErrorResponse(ErrorCodes errorCode, ErrorMessages errorMessage) {
        Response response = Response.builder()
                .code(Codes.FAILED)
                .errorCode(errorCode)
                .errorMessage(errorMessage)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}