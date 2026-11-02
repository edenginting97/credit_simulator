package com.example.factory.car;

import com.example.factory.InstallmentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OldCar extends InstallmentCalculator {

    @Override
    public boolean exceedsMinimumDownPayment(BigDecimal downPayment, BigDecimal totalLoan) {
        var maxAllowedDownPayment = totalLoan
                .multiply(new BigDecimal("0.25"))
                .setScale(2, RoundingMode.HALF_UP);
        return downPayment.compareTo(maxAllowedDownPayment) < 0;
    }

    @Override
    protected BigDecimal getBaseInterestRate() {
        return new BigDecimal("0.08");
    }
}
