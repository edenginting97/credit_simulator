package com.example.factory;

import java.math.BigDecimal;

public interface InstallmentCalculator {

    void validateVehicleYear(int num);

    void calculateTotalLoan(BigDecimal amount);

    void validateLoanTenure(int year);

    void calculateDownPayment(BigDecimal amount);
}
