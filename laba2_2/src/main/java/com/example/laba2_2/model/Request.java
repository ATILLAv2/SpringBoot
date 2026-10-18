package com.example.laba2_2.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Request {

    @NotBlank(message = "UID не может быть пустым")
    private String uid;

    private String operationUid;

    private Systems systemName;

    private String systemTime;

    private String source;

    @NotNull(message = "Позиция не может быть пустой")
    private Positions position;

    @NotNull(message = "Зарплата не может быть пустой")
    private Double salary;

    @NotNull(message = "Премия не может быть пустой")
    private Double bonus;

    @NotNull(message = "Количество дней не может быть пустым")
    private Integer workDays;

    private Integer communicationId;

    private Integer templateId;

    private Integer productCode;

    private Integer smsCode;

    @Override
    public String toString() {
        return "{" +
                "uid='" + uid + '\'' +
                ", operationUid='" + operationUid + '\'' +
                ", systemName=" + systemName +
                ", systemTime='" + systemTime + '\'' +
                ", source='" + source + '\'' +
                ", position=" + position +
                ", salary=" + salary +
                ", bonus=" + bonus +
                ", workDays=" + workDays +
                ", communicationId=" + communicationId +
                ", templateId=" + templateId +
                ", productCode=" + productCode +
                ", smsCode=" + smsCode +
                '}';
    }
}