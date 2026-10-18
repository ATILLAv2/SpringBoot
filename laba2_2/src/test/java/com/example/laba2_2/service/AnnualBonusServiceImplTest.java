package com.example.laba2_2.service;

import com.example.laba2_2.model.Positions;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnnualBonusServiceImplTest {

    @Test
    void calculate() {
        AnnualBonusServiceImpl service = new AnnualBonusServiceImpl();
        Positions position = Positions.HR;
        double salary = 100000.00;
        double bonus = 2.0;
        int workDays = 243;

        double result = service.calculate(position, salary, bonus, workDays);

        int daysInYear = Year.now().isLeap() ? 366 : 365;
        double expected = salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;

        assertEquals(expected, result, 0.0001);
    }
}