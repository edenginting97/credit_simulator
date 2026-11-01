package com.example.factory.motorcycle;

import com.example.dto.InstallmentResult;
import com.example.dto.VehicleLoan;
import com.example.factory.InstallmentCalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class OldMotorcycle extends InstallmentCalculator {

    @Override
    public boolean validateDownPayment(BigDecimal downPayment, BigDecimal totalLoan) {
        var maxAllowedDownPayment = totalLoan
                .multiply(new BigDecimal("0.25"))
                .setScale(2, RoundingMode.HALF_UP);
        return downPayment.compareTo(maxAllowedDownPayment) < 0;
    }

    @Override
    public List<InstallmentResult> calculateMonthlyInstallment(VehicleLoan loan) {
        return null;
    }

    @Override
    protected BigDecimal getBaseInterestRate() {
        return new BigDecimal("0.09");
    }
}
