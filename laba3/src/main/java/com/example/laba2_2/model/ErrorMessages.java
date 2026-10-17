package com.example.laba2_2.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ErrorMessages {
    EMPTY(""),
    VALIDATION_ERROR("Ошибка валидации"),
    UNSUPPORTED_CODE_ERROR("Не поддерживаемая ошибка"),
    UNKNOWN_ERROR("Произошла непредвиденная ошибка");

    private final String description;

    ErrorMessages(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return description;
    }
}