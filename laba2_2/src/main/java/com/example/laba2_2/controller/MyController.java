package com.example.laba2_2.controller;

import com.example.laba2_2.exception.UnsupportedCodeException;
import com.example.laba2_2.exception.ValidationFailedException;
import com.example.laba2_2.model.*;
import com.example.laba2_2.service.*;
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
    private final ModifyRequestService modifySourceRequestService;
    private final ModifyRequestService modifyRequestServiceRestTemplate;
    private final AnnualBonusService annualBonusService;
    private final QuarterlyBonusService quarterlyBonusService;

    @Autowired
    public MyController(
            ValidationService validationService,
            @Qualifier("ModifySystemTimeResponseService") ModifyResponseService modifySystemTimeResponseService,
            @Qualifier("ModifyOperationUidResponseService") ModifyResponseService modifyOperationUidResponseService,
            @Qualifier("ModifySourceRequestService") ModifyRequestService modifySourceRequestService,
            @Qualifier("ModifyRequestServiceRestTemplate") ModifyRequestService modifyRequestServiceRestTemplate,
            AnnualBonusService annualBonusService,
            QuarterlyBonusService quarterlyBonusService) {
        this.validationService = validationService;
        this.modifySystemTimeResponseService = modifySystemTimeResponseService;
        this.modifyOperationUidResponseService = modifyOperationUidResponseService;
        this.modifySourceRequestService = modifySourceRequestService;
        this.modifyRequestServiceRestTemplate = modifyRequestServiceRestTemplate;
        this.annualBonusService = annualBonusService;
        this.quarterlyBonusService = quarterlyBonusService;
    }

    @PostMapping(value = "/feedback")
    public ResponseEntity<Response> feedback(@Valid @RequestBody Request request,
                                             BindingResult bindingResult) throws ValidationFailedException, UnsupportedCodeException {

        log.info("Получен запрос: {}", request);

        validationService.isValid(bindingResult);
        validationService.checkUnsupportedCode(request);

        double annualBonus = annualBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays());
        Double quarterlyBonus = null;

        try {
            quarterlyBonus = quarterlyBonusService.calculate(request.getPosition(), request.getSalary(), request.getBonus(), request.getWorkDays());
        } catch (Exception ignored) {
        }

        Response response = Response.builder()
                .uid(request.getUid())
                .operationUid(request.getOperationUid())
                .systemTime(DateTimeUtil.getCustomFormat().format(new Date()))
                .code(Codes.SUCCESS)
                .errorCode(ErrorCodes.EMPTY)
                .errorMessage(ErrorMessages.EMPTY)
                .salary(request.getSalary())
                .bonus(request.getBonus())
                .workDays(request.getWorkDays())
                .annualBonus(annualBonus)
                .quarterlyBonus(quarterlyBonus)
                .build();

        modifySourceRequestService.modify(request);
        modifyRequestServiceRestTemplate.modify(request);

        modifySystemTimeResponseService.modify(response);
        modifyOperationUidResponseService.modify(response);

        log.info("Отправлен ответ: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}