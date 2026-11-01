package com.example.dto;

public class Installment {

    private String state;

    private VehicleLoan vehicleLoan;

    public String getState() {
        return state;
    }

    public void setState(String state) {
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
