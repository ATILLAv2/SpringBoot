package com.example.laba2_2.service;

import com.example.laba2_2.model.Positions;

public interface AnnualBonusService {
    double calculate(Positions position, double salary, double bonus, int workDays);
}