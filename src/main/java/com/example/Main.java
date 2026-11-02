package com.example;

import com.example.builder.Director;
import com.example.cmd.MenuCommand;
import com.example.controller.CreditSimulatorController;
import com.example.session.CalculationSession;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        var menuCmd = new MenuCommand();

        var director = new Director(new CalculationSession());
        var controller = new CreditSimulatorController(scanner, menuCmd, director);
        controller.execute(args);
    }
}