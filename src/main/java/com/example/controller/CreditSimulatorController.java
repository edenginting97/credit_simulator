package com.example.controller;

import com.example.cmd.MenuCommand;

import java.util.Scanner;

public class CreditSimulatorController {

    public void execute(String[] args) {
        var scanner = new Scanner(System.in);

        System.out.println("Welcome to Credit Simulator \uD83D\uDE42");
        System.out.println("Type 'show' to see all commands");

        MenuCommand menuCmd = new MenuCommand();
        menuCmd.run(scanner);

        scanner.close();
    }
}
