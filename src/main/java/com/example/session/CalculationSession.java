package com.example.session;

import com.example.dto.Installment;
import com.example.dto.VehicleLoan;
import com.example.enums.CalculationState;
import com.example.factory.InstallmentCalculator;
import com.example.factory.InstallmentFactory;
import com.example.factory.car.CarFactory;
import com.example.factory.motorcycle.MotorcycleFactory;

import java.math.BigDecimal;

public class CalculationSession {

    private static final String MOTOR = "Motor";

    private static final String MOBIL = "Mobil";

    private static final String BARU = "Baru";

    private static final String BEKAS = "Bekas";

    private Installment installment = new Installment();

    private VehicleLoan vehicleLoan = new VehicleLoan();

    private InstallmentFactory factory;

    private InstallmentCalculator calculator;

    public void selectVehicle(String vehicle) {
        if (vehicle.equalsIgnoreCase(MOTOR)) {
            this.factory = new MotorcycleFactory();
            this.vehicleLoan.setVehicleType(MOTOR);
        } else {
            this.factory = new CarFactory();
            this.vehicleLoan.setVehicleType(MOBIL);
        }

        this.installment.setState(CalculationState.SELECT_CONDITION);
    }

    public void selectCondition(String condition) {
        if (condition.equalsIgnoreCase(BEKAS)) {
            this.calculator = factory.createOldVehicle();
            this.vehicleLoan.setVehicleCondition(BEKAS);
        } else {
            this.calculator = factory.createNewVehicle();
            this.vehicleLoan.setVehicleCondition(BARU);
        }

        this.installment.setState(CalculationState.INPUT_VEHICLE_YEAR);
        this.installment.setVehicleLoan(vehicleLoan);
    }

    public void setVehicleYear(int year) {
        if (this.calculator.isInvalidVehicleYear(year)) {
            System.err.println("Vehicle year must be exactly 4 digits and new vehicles must be from the current year or the previous year");
        } else {
            this.vehicleLoan.setVehicleYear(year);
            this.installment.setState(CalculationState.INPUT_TOTAL_LOAN);
            this.installment.setVehicleLoan(vehicleLoan);
        }
    }

    public void setTotalLoan(BigDecimal amount) {
        if (this.calculator.exceedsMaximumTotalLoan(amount)) {
            System.err.println("Total loan cannot be greater than 1 million");
        } else {
            this.vehicleLoan.setTotalLoanAmount(amount);
            this.installment.setState(CalculationState.INPUT_LOAN_TENURE);
            this.installment.setVehicleLoan(vehicleLoan);
        }
    }

    public void setLoanTenure(int year) {
        if (this.calculator.isLoanTenureOutOfRange(year)) {
            System.err.println("Loan tenure must be between 1 and 6");
        } else {
            this.vehicleLoan.setLoanTenure(year);
            this.installment.setState(CalculationState.INPUT_DOWN_PAYMENT);
            this.installment.setVehicleLoan(vehicleLoan);
        }
    }

    public void setDownPayment(BigDecimal amount) {
        if (calculator.exceedsMinimumDownPayment(amount, vehicleLoan.getTotalLoanAmount())) {
            System.err.println("Down payment cannot be lower than 35% for 'New Vehicle' and 25% for 'Old Vehicle'");
        } else {
            vehicleLoan.setDownPayment(amount);
            System.out.println(calculator.calculateMonthlyInstallment(vehicleLoan));
            this.reset();
        }
    }

    public void reset() {
        this.installment = new Installment();
        this.vehicleLoan = new VehicleLoan();

        this.factory = null;
        this.calculator = null;
    }

    public Installment getInstallment() {
        return installment;
    }

    public void setInstallment(Installment installment) {
        this.installment = installment;
        this.vehicleLoan = installment.getVehicleLoan();

        restoreCalculator();
    }

    private void restoreCalculator() {
        var vehicleType = vehicleLoan.getVehicleType();
        var condition = vehicleLoan.getVehicleCondition();

        if (vehicleType != null) {
            if (vehicleType.equalsIgnoreCase(MOBIL)) {
                this.factory = new CarFactory();
            } else {
                this.factory = new MotorcycleFactory();
            }
        }

        if (condition != null) {
            if (condition.equalsIgnoreCase(BEKAS)) {
                this.calculator = factory.createOldVehicle();
            } else {
                this.calculator = factory.createNewVehicle();
            }
        }
    }
}
