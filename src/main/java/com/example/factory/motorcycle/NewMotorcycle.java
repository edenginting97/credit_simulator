package com.example.factory.motorcycle;

import com.example.factory.InstallmentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Year;

public class NewMotorcycle extends InstallmentCalculator {

    @Override
    public boolean isInvalidVehicleYear(int num) {
        return num < Year.now().getValue() - 1 || super.isInvalidVehicleYear(num);
    }

    @Override
    public boolean exceedsMinimumDownPayment(BigDecimal downPayment, BigDecimal totalLoan) {
        var maxAllowedDownPayment = totalLoan
                .multiply(new BigDecimal("0.35"))
                .setScale(2, RoundingMode.HALF_UP);
        return downPayment.compareTo(maxAllowedDownPayment) < 0;
    }

    @Override
    protected BigDecimal getBaseInterestRate() {
        return new BigDecimal("0.09");
    }
}
