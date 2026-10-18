package com.example.laba2_2.service;

import com.example.laba2_2.exception.NotManagerException;
import com.example.laba2_2.model.Positions;
import org.springframework.stereotype.Service;

@Service
public class QuarterlyBonusServiceImpl implements QuarterlyBonusService {

    @Override
    public double calculate(Positions position, double salary, double bonus, int workDays) {
        if (!position.isManager()) {
            throw new NotManagerException("Квартальная премия выплачивается только менеджерам");
        }
        return salary * bonus * 90 * position.getPositionCoefficient() / workDays;
    }
}