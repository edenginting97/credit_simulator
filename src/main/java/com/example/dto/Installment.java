package com.example.dto;

import com.example.enums.CalculationState;

public class Installment {

    private CalculationState state;

    private VehicleLoan vehicleLoan;

    public CalculationState getState() {
        return state;
    }

    public void setState(CalculationState state) {
        this.state = state;
    }

    public VehicleLoan getVehicleLoan() {
        return vehicleLoan;
    }

    public void setVehicleLoan(VehicleLoan vehicleLoan) {
        this.vehicleLoan = vehicleLoan;
    }

    @Override
    public String toString() {
        return "Installment{" +
                "state='" + state + '\'' +
                ", vehicleLoan=" + vehicleLoan +
                '}';
    }
}
