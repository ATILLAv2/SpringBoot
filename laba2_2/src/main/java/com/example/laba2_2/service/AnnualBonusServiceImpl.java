package com.example.laba2_2.service;

import com.example.laba2_2.model.Positions;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class AnnualBonusServiceImpl implements AnnualBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays) {
        int daysInYear = Year.now().isLeap() ? 366 : 365;

        return salary * bonus * daysInYear * position.getPositionCoefficient() / workDays;
    }
}