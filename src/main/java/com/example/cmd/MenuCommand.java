package com.example.cmd;

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
                case "load" -> {
                    // TODO: call API and calculate installment
                }
                case "exit" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Unsupported command. check available commands using 'show'");
            }
        }
    }
}
