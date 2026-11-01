package com.example.cmd;

import com.example.dto.Installment;
import com.example.dto.VehicleLoan;
import com.example.factory.InstallmentCalculator;
import com.example.factory.InstallmentFactory;
import com.example.factory.car.CarFactory;
import com.example.factory.motorcycle.MotorcycleFactory;
import com.example.utils.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculationCommand {

    private static boolean init = true;

    private static List<Installment> installments = new ArrayList<>();

    public void start(Scanner scanner, Display display) {
        if (init) {
            display.printCalculationCommands();
            init = false;
        }

        System.out.println("You are currently on the calculation");

        var installment = new Installment();
        var vehicleLoan = new VehicleLoan();
        InstallmentFactory vehicleType = new CarFactory();
        var calculator = vehicleType.createNewVehicle();

        var calculating = true;
        while (calculating) {
            if (vehicleLoan.getDownPayment() != null) {
                System.out.println("Result: " + vehicleLoan);
                vehicleLoan = new VehicleLoan();
            }

            System.out.println("What's next?");
            var cmd = scanner.nextLine().trim().toLowerCase();

            if (cmd.contains("fetch ")) {
                var idx = Integer.parseInt(cmd.split(" ")[1]);
                installment = installments.get(idx);
            } else {
                switch (cmd) {
                    case "next" -> calculateInstallment(installment, scanner, calculator, vehicleType, vehicleLoan);
                    case "save" -> {
                        installments.add(installment);
                        installment = new Installment();
                    }
                    case "list" -> System.out.println(installments);
                    case "back" -> calculating = false;
                    case "show" -> display.printCalculationCommands();
                    default -> System.out.println("Unsupported command. check available command using 'show'");
                }
            }
        }
    }

    private void calculateInstallment(
            Installment installment,
            Scanner scanner,
            InstallmentCalculator calculator,
            InstallmentFactory vehicleType,
            VehicleLoan vehicleLoan
    ) {
        var state = installment.getState() == null ? "vehicle" : installment.getState();
        switch (state) {
            case "condition" -> {
                System.out.println("Input kendaraan Bekas|Baru");
                var condition = scanner.nextLine().trim().toLowerCase();

                var cond = "Baru";
                if (condition.equalsIgnoreCase("bekas")) {
                    calculator = vehicleType.createOldVehicle();
                    cond = "Bekas";
                }

                vehicleLoan.setVehicleCondition(cond);
                installment.setState("vehicle_year");
                installment.setVehicleLoan(vehicleLoan);
            }
            case "vehicle_year" -> {
                System.out.println("Input tahun kendaraan");
                var year = scanner.nextInt();
                scanner.nextLine();

                if (calculator.validateVehicleYear(year)) {
                    System.out.println("Vehicle year must be exactly 4 digits");
                    break;
                }

                vehicleLoan.setVehicleYear(year);
                installment.setState("total_loan");
                installment.setVehicleLoan(vehicleLoan);
            }
            case "total_loan" -> {
                System.out.println("Input jumlah pinjaman total");
                var amount = scanner.nextBigDecimal();
                scanner.nextLine();

                if (calculator.validateTotalLoan(amount)) {
                    System.out.println("Total loan cannot be greater than 1 million");
                    break;
                }

                vehicleLoan.setTotalLoanAmount(amount);
                installment.setState("loan_tenure");
                installment.setVehicleLoan(vehicleLoan);
            }
            case "loan_tenure" -> {
                System.out.println("Input tenor pinjaman");
                var year = scanner.nextInt();
                scanner.nextLine();

                if (calculator.validateLoanTenure(year)) {
                    System.out.println("Loan tenure must be between 1 and 6");
                    break;
                }
                vehicleLoan.setLoanTenure(year);
                installment.setState("down_payment");
                installment.setVehicleLoan(vehicleLoan);
            }
            case "down_payment" -> {
                System.out.println("Input jumlah DP");
                var amount = scanner.nextBigDecimal();
                scanner.nextLine();

                if (calculator.validateDownPayment(amount, vehicleLoan.getTotalLoanAmount())) {
                    System.out.println("Down payment cannot be lower than 35% for 'New Vehicle' and 25% for 'Old Vehicle'");
                    break;
                }

                vehicleLoan.setDownPayment(amount);
                installment = new Installment();

                calculator.calculateMonthlyInstallment(vehicleLoan);
            }
            default -> {
                System.out.println("Input jenis kendaraan Motor|Mobil");
                var vehicle = scanner.nextLine().trim().toLowerCase();

                var type = "Mobil";
                if (vehicle.equals("motor")) {
                    vehicleType = new MotorcycleFactory();
                    type = "Motor";
                }

                vehicleLoan.setVehicleType(type);
                installment.setState("condition");
            }
        }
    }
}
