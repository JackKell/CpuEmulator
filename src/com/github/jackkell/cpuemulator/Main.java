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
            // Get the user input and reduce the string to lower case
            userInput = reader.nextLine().toLowerCase();
            try {
                // Create a command based on the user's input
                currentCommand = new Command(userInput);
                // Execute the users command
                currentCommand.execute();
            }
            // If an exception happens tell the user why the exception happened
            catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("Invalid format: " + userInput);
            }

        } while (!Objects.equals(userInput, "exit"));
        System.out.println("Thank you for using C.A.x.E");
        reader.close();
    }
}
