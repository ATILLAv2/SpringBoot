package com.example.laba2_2.controller;

import com.example.laba2_2.exception.UnsupportedCodeException;
import com.example.laba2_2.exception.ValidationFailedException;
import com.example.laba2_2.model.*;
import com.example.laba2_2.service.ModifyResponseService;
import com.example.laba2_2.service.ValidationService;
import com.example.laba2_2.util.DateTimeUtil;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Slf4j
@RestController
public class MyController {

    private final ValidationService validationService;
    private final ModifyResponseService modifySystemTimeResponseService;
    private final ModifyResponseService modifyOperationUidResponseService;

    @Autowired
    public MyController(ValidationService validationService,
                        @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifySystemTimeResponseService,
                        @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyOperationUidResponseService) {
        this.validationService = validationService;
        this.modifySystemTimeResponseService = modifySystemTimeResponseService;
        this.modifyOperationUidResponseService = modifyOperationUidResponseService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) {

        log.info("Получен запрос: {}", request);

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .build();

        try {
            validationService.isValid(bindingResult);
            validationService.checkUnsupportedCode(request);
        } catch (ValidationFailedException e) {
            log.error("Ошибка валидации: {}", bindingResult.getAllErrors());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.VALIDATION_EXCEPTION);
            response.setErrorMessage(ErrorMessages.VALIDATION_ERROR);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (UnsupportedCodeException e) {
            log.error("Неподдерживаемый код: {}", request.getUid());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNSUPPORTED_CODE_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNSUPPORTED_CODE_ERROR);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.error("Непредвиденная ошибка: {}", e.getMessage());
            response.setCode(Codes.FAILED);
            response.setErrorCode(ErrorCodes.UNKNOWN_EXCEPTION);
            response.setErrorMessage(ErrorMessages.UNKNOWN_ERROR);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        modifySystemTimeResponseService.modify(response);
        modifyOperationUidResponseService.modify(response);

        log.info("Отправлен ответ: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}