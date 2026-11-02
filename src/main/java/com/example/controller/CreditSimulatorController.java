package com.example.controller;

import com.example.builder.Director;
import com.example.cmd.MenuCommand;
import com.example.service.VehicleLoanFileService;
import com.example.session.CalculationSession;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreditSimulatorController {

    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Welcome to Credit Simulator \uD83D\uDE42");
            System.out.println("Type 'show' to see all commands");
            var scanner = new Scanner(System.in);

            MenuCommand menuCmd = new MenuCommand();
            menuCmd.run(scanner);

            scanner.close();
        } else {
            var filePath = args[0];
            try {
                var loan = VehicleLoanFileService.load(filePath);
                var director = new Director(new CalculationSession());
                director.calculateCredit(loan);
            } catch (FileNotFoundException e) {
                System.err.println("Error: The file at '" + filePath + "' could not be found");
            }
        }
    }
}
