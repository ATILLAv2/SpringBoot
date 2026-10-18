package com.example.laba2_2.service;

import com.example.laba2_2.exception.NotManagerException;
import com.example.laba2_2.model.Positions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuarterlyBonusServiceImplTest {

    private final QuarterlyBonusServiceImpl service = new QuarterlyBonusServiceImpl();

    @Test
    void calculateForManager() {
        Positions position = Positions.TL;
        double salary = 100000.0;
        double bonus = 1.5;
        int workDays = 60;

        double result = service.calculate(position, salary, bonus, workDays);

        assertTrue(result > 0);
    }

    @Test
    void calculateForNonManager() {
        Positions position = Positions.DEV;
        double salary = 100000.0;
        double bonus = 1.5;
        int workDays = 60;

        assertThrows(NotManagerException.class, () -> {
            service.calculate(position, salary, bonus, workDays);
        });
    }
}