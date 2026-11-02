package com.example.controller;

import com.example.builder.Director;
import com.example.cmd.MenuCommand;
import com.example.service.VehicleLoanFileService;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CreditSimulatorController {

    private final Scanner scanner;

    private final MenuCommand menuCmd;

    private final Director director;

    public CreditSimulatorController(Scanner scanner, MenuCommand menuCmd, Director director) {
        this.scanner = scanner;
        this.menuCmd = menuCmd;
        this.director = director;
    }

    public void execute(String[] args) {
        if (args.length < 1) {
            System.out.println("Welcome to Credit Simulator \uD83D\uDE42");
            System.out.println("Type 'show' to see all commands");

            menuCmd.run(scanner);
            scanner.close();
        } else {
            var filePath = args[0];
            try {
                var loan = VehicleLoanFileService.load(filePath);
                director.calculateCredit(loan);
            } catch (FileNotFoundException e) {
                System.err.println("Error: The file at '" + filePath + "' could not be found");
            }
        }
    }
}
