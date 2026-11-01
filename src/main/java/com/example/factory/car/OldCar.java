package com.example.factory.car;

import com.example.factory.InstallmentCalculator;

import java.math.BigDecimal;

public class OldCar implements InstallmentCalculator {

    @Override
    public void validateVehicleYear(int num) {

    }

    @Override
    public void calculateTotalLoan(BigDecimal amount) {

    }

    @Override
    public void validateLoanTenure(int year) {

    }

    @Override
    public void calculateDownPayment(BigDecimal amount) {

    }
}
