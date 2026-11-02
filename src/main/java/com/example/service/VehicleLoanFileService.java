package com.example.service;

import com.example.dto.VehicleLoan;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VehicleLoanFileService {

    public static VehicleLoan load(String filePath) throws FileNotFoundException {
        var file = new File(filePath);
        try (var scanner = new Scanner(file)) {
            System.out.println("----- Reading File Content -----");

            var loan = new VehicleLoan();
            while (scanner.hasNextLine()) {
                var line = scanner.nextLine().trim();

                if (line.isBlank() || !line.contains(":")) continue;

                var parts = line.split(":", 2);
                var key = parts[0].trim();
                var value = parts[1].trim();

                switch (key) {
                    case "Jenis Kendaraan" -> loan.setVehicleType(value);
                    case "Kondisi Kendaraan" -> loan.setVehicleCondition(value);
                    case "Tahun Kendaraan" -> loan.setVehicleYear(Integer.parseInt(value));
                    case "Jumlah Total Pinjaman" -> loan.setTotalLoanAmount(new BigDecimal(value));
                    case "Tenor Pinjaman" -> loan.setLoanTenure(Integer.parseInt(value));
                    case "Jumlah DP" -> loan.setDownPayment(new BigDecimal(value));
                    default -> System.out.println("Unknown field encountered");
                }
            }

            return loan;
        }
    }
}
