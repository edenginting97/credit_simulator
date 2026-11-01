package com.example.dto;

import java.math.BigDecimal;

public class InstallmentResult {

    private int year;

    private BigDecimal interestRate;

    private BigDecimal monthlyInstallment;

    private BigDecimal yearlyInstallment;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getMonthlyInstallment() {
        return monthlyInstallment;
    }

    public void setMonthlyInstallment(BigDecimal monthlyInstallment) {
        this.monthlyInstallment = monthlyInstallment;
    }

    public BigDecimal getYearlyInstallment() {
        return yearlyInstallment;
    }

    public void setYearlyInstallment(BigDecimal yearlyInstallment) {
        this.yearlyInstallment = yearlyInstallment;
    }
}
