package com.example.laba2_2.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Systems {
    ERP("Enterprise Resource Planning"),
    CRM("Customer Relationship Management"),
    WMS("Warehouse Management System");

    private final String description;

    Systems(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Systems fromValue(String value) {
        for (Systems s : Systems.values()) {
            if (s.name().equalsIgnoreCase(value)
                    || s.description.equalsIgnoreCase(value)) {
                return s;
            }
        }
        throw new IllegalArgumentException("Неизвестное значение systemName: " + value);
    }

    @Override
    public String toString() {
        return name();
    }
}