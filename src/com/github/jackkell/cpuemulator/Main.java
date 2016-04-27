package com.github.jackkell.cpuemulator;

import com.github.jackkell.cpuemulator.util.Command;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to CPU ASM x86 Emulator or C.A.x.E for short.");
        Scanner reader = new Scanner(System.in);
        Command currentCommand;
        String userInput = "";
        do {
            System.out.println("Enter a command.");
            System.out.print("--> ");
            userInput = reader.nextLine().toLowerCase();
            try {
                currentCommand = new Command(userInput);
                currentCommand.execute();
            } catch (Exception e) {
                System.out.println("Invalid format: " + userInput);
            }

        } while (!Objects.equals(userInput, "exit"));
        reader.close();
    }
}
