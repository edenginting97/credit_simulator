package com.example.dto;

import java.math.BigDecimal;

public class VehicleLoan {

    private String vehicleType;

    private String vehicleCondition;

    private Integer vehicleYear;

    private BigDecimal totalLoanAmount;

    private Integer loanTenure;

    private BigDecimal downPayment;

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getVehicleCondition() {
        return vehicleCondition;
    }

    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }

    public Integer getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(Integer vehicleYear) {
        this.vehicleYear = vehicleYear;
    }

    public BigDecimal getTotalLoanAmount() {
        return totalLoanAmount;
    }

    public void setTotalLoanAmount(BigDecimal totalLoanAmount) {
        this.totalLoanAmount = totalLoanAmount;
    }

    public Integer getLoanTenure() {
        return loanTenure;
    }

    public void setLoanTenure(Integer loanTenure) {
        this.loanTenure = loanTenure;
    }

    public BigDecimal getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(BigDecimal downPayment) {
        this.downPayment = downPayment;
    }

    @Override
    public String toString() {
        return "VehicleLoan{" +
                "vehicleType='" + vehicleType + '\'' +
                ", vehicleCondition='" + vehicleCondition + '\'' +
                ", vehicleYear=" + vehicleYear +
                ", totalLoanAmount=" + totalLoanAmount +
                ", loanTenure=" + loanTenure +
                ", downPayment=" + downPayment +
                '}';
    }
}
