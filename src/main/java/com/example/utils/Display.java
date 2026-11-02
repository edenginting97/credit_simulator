package com.example.utils;

public class Display {

    public void printMenuCommands() {
        System.out.println("""
                ===== List Commands =====
                'show' to list all commands
                'start' to begin calculation
                'load' to calculate existing data
                'exit' to quit program data
                """);
    }

    public void printCalculationCommands() {
        System.out.println("""
                ===== List Commands =====
                'next' to continue calculation
                'save' to store current session and start new session
                'list' to check all stored sessions
                'back' to go to menu
                'switch <index>' to fetch saved data
                'show' to list all commands
                """);
    }
}
