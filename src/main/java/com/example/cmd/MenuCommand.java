package com.example.cmd;

import com.example.builder.Director;
import com.example.client.ApiClient;
import com.example.session.CalculationSession;
import com.example.utils.Display;

import java.util.Scanner;

public class MenuCommand {

    public void run(Scanner scanner) {
        var display = new Display();
        var calculationCmd = new CalculationCommand();

        var running = true;
        while (running) {
            System.out.println("You are currently on the menu");

            var cmd = scanner.nextLine().trim().toLowerCase();
            switch (cmd) {
                case "show" -> display.printMenuCommands();
                case "start" -> calculationCmd.start(scanner, display);
                case "load" -> calculateExistingCredit();
                case "exit" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Unsupported command. check available commands using 'show'");
            }
        }
    }

    private void calculateExistingCredit() {
        var client = new ApiClient();

        try {
            var vehicleLoan = client.get("https://mocki.io/v1/477170fb-dd63-4e84-8ee7-759d941da043");
            var director = new Director(new CalculationSession());
            director.calculateCredit(vehicleLoan);
        } catch (Exception ex) {
            throw new RuntimeException("Failed to call mocki: " + ex.getMessage());
        }
    }
}
