package com.example.factory;

import com.example.dto.InstallmentResult;
import com.example.dto.VehicleLoan;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

abstract public class InstallmentCalculator {

    public boolean isInvalidVehicleYear(int num) {
        return num > 9999;
    }

    public boolean exceedsMaximumTotalLoan(BigDecimal amount) {
        BigDecimal loanLimit = new BigDecimal(1_000_000_000);
        return amount.compareTo(loanLimit) > 0;
    }

    public boolean isLoanTenureOutOfRange(int year) {
        return year < 1 || year > 6;
    }

    public List<InstallmentResult> calculateMonthlyInstallment(VehicleLoan loan) {
        var principal = loan.getTotalLoanAmount().subtract(loan.getDownPayment());
        List<InstallmentResult> results = new ArrayList<>();

        for (int year = 1; year <= loan.getLoanTenure(); year++) {

            BigDecimal interestRate = getInterestRate(year);

            BigDecimal totalRepayment = principal.multiply(
                    BigDecimal.ONE.add(interestRate)
            );

            int remainingMonths = (loan.getLoanTenure() - year + 1) * 12;

            BigDecimal monthlyInstallment = totalRepayment.divide(
                    BigDecimal.valueOf(remainingMonths),
                    2,
                    RoundingMode.HALF_UP
            );

            BigDecimal yearlyInstallment = monthlyInstallment.multiply(
                    BigDecimal.valueOf(12)
            );

            InstallmentResult result = new InstallmentResult();
            result.setYear(year);
            result.setInterestRate(interestRate);
            result.setMonthlyInstallment(monthlyInstallment);
            result.setYearlyInstallment(yearlyInstallment);

            results.add(result);

            principal = totalRepayment.subtract(yearlyInstallment);
        }

        return results;
    }

    public abstract boolean exceedsMinimumDownPayment(BigDecimal amount, BigDecimal totalLoan);

    protected abstract BigDecimal getBaseInterestRate();

    private BigDecimal getInterestRate(int year) {
        BigDecimal baseRate = getBaseInterestRate();

        BigDecimal yearlyIncrease = new BigDecimal("0.001")
                .multiply(BigDecimal.valueOf(year - 1));

        BigDecimal twoYearIncrease = new BigDecimal("0.004")
                .multiply(BigDecimal.valueOf((year - 1) / 2));

        return baseRate
                .add(yearlyIncrease)
                .add(twoYearIncrease);
    }
}
