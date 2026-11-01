package com.example.controller;

import com.example.utils.Display;

import java.util.Scanner;

public class CreditSimulatorController {

    public void execute(String[] args) {
        var scanner = new Scanner(System.in);
        var display = new Display();

        System.out.println("Welcome to Credit Simulator \uD83D\uDE42");
        System.out.println("Type 'show' to see all commands");

        var running = true;
        while (running) {
            var cmd = scanner.nextLine().trim().toLowerCase();
            switch (cmd) {
                case "show":
                    display.printMenuCommands();
                    break;
                case "start":
                    // TODO: start installment calculation step by step
                    break;
                case "load":
                    // TODO: call API and calculate installment
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.out.println("Unsupported command. check available commands using 'show'");
                    break;
            }
        }

        scanner.close();
    }
}
