package com.example.builder;

import com.example.dto.VehicleLoan;
import com.example.session.CalculationSession;

public class Director {

    private final CalculationSession session;

    public Director(CalculationSession session) {
        this.session = session;
    }

    public void calculateCredit(VehicleLoan loan) {
        session.selectVehicle(loan.getVehicleType());
        session.selectCondition(loan.getVehicleCondition());
        session.setVehicleYear(loan.getVehicleYear());
        session.setTotalLoan(loan.getTotalLoanAmount());
        session.setLoanTenure(loan.getLoanTenure());
        session.setDownPayment(loan.getDownPayment());
    }
}
