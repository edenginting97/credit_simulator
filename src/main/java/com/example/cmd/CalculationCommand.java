package com.example.cmd;

import com.example.dto.Installment;
import com.example.enums.CalculationState;
import com.example.session.CalculationSession;
import com.example.utils.Display;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CalculationCommand {

    private static boolean init = true;

    private static final List<Installment> installments = new ArrayList<>();

    public void start(Scanner scanner, Display display) {
        if (init) {
            display.printCalculationCommands();
            init = false;
        }

        System.out.println("You are currently on the calculation");

        var session = new CalculationSession();

        var calculating = true;
        while (calculating) {
            System.out.println("What's next?");
            var cmd = scanner.nextLine().trim().toLowerCase();

            if (cmd.contains("switch ")) {
                var idx = Integer.parseInt(cmd.split(" ")[1]);
                if (installments.size() >= idx + 1) {
                    session.setInstallment(installments.get(idx));
                    installments.remove(idx);
                } else {
                    System.out.println("No data available at index " + idx);
                }
            } else {
                switch (cmd) {
                    case "next" -> calculateInstallment(session, scanner);
                    case "save" -> {
                        installments.add(session.getInstallment());
                        session.reset();
                    }
                    case "list" -> System.out.println(installments);
                    case "back" -> calculating = false;
                    case "show" -> display.printCalculationCommands();
                    default -> System.out.println("Unsupported command. check available command using 'show'");
                }
            }
        }
    }

    private void calculateInstallment(CalculationSession session, Scanner scanner) {
        var state = session.getInstallment().getState() == null ? CalculationState.SELECT_VEHICLE : session.getInstallment().getState();
        try {
            switch (state) {
                case SELECT_CONDITION -> {
                    System.out.println("Input kendaraan Bekas|Baru");
                    var condition = scanner.nextLine().trim();
                    session.selectCondition(condition);
                }
                case INPUT_VEHICLE_YEAR -> {
                    System.out.println("Input tahun kendaraan");
                    var year = scanner.nextInt();
                    scanner.nextLine();

                    session.setVehicleYear(year);
                }
                case INPUT_TOTAL_LOAN -> {
                    System.out.println("Input jumlah pinjaman total");
                    var amount = scanner.nextBigDecimal();
                    scanner.nextLine();

                    session.setTotalLoan(amount);
                }
                case INPUT_LOAN_TENURE -> {
                    System.out.println("Input tenor pinjaman");
                    var year = scanner.nextInt();
                    scanner.nextLine();

                    session.setLoanTenure(year);
                }
                case INPUT_DOWN_PAYMENT -> {
                    System.out.println("Input jumlah DP");
                    var amount = scanner.nextBigDecimal();
                    scanner.nextLine();

                    session.setDownPayment(amount);
                    session.printResult();
                    session.reset();
                }
                default -> {
                    System.out.println("Input jenis kendaraan Motor|Mobil");
                    var vehicle = scanner.nextLine().trim();
                    session.selectVehicle(vehicle);
                }
            }
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());
        }
    }
}
